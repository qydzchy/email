package com.ruoyi.email.service.impl;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.ProxyTypeEnum;
import com.ruoyi.common.enums.email.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.*;
import com.ruoyi.email.domain.bo.EmailOperateParamBO;
import com.ruoyi.email.domain.dto.task.EditTaskDTO;
import com.ruoyi.email.domain.vo.*;
import com.ruoyi.email.mapper.*;
import com.ruoyi.email.service.*;
import com.ruoyi.email.service.handler.email.*;
import com.ruoyi.email.util.ThreadPoolPullService;
import com.ruoyi.email.util.ThreadPoolSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮箱任务Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class TaskServiceImpl implements ITaskService
{
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private MailContext mailContext;
    @Resource
    private IHostService hostService;
    @Resource
    private ITaskEmailService taskEmailService;
    @Resource
    private ITaskEmailContentService taskEmailContentService;
    @Resource
    private ITaskAttachmentService taskAttachmentService;
    @Resource
    private ITaskEmailAttachmentService taskEmailAttachmentService;
    @Resource
    private GeneralMapper generalMapper;
    @Resource
    private OtherConfigMapper otherConfigMapper;
    @Resource
    private TransceiverRuleMapper transceiverRuleMapper;
    @Resource
    private BlacklistMapper blacklistMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${email.pull.path}")
    private String emailPath;
    @Value("${email.attachment.path}")
    private String attachmentPath;

    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询邮箱任务列表
     *
     * @return 邮箱任务
     */
    @Override
    public List<TaskListVO> listTask()
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        return taskMapper.listTask(userId);
    }

    /**
     * 解绑
     * @param id
     * @return
     */
    @Override
    public Boolean unbind(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        int result = taskMapper.unbindTaskById(id, userId);
        return result > 0 ? true : false;
    }

    @Override
    public List<HomeListTaskVO> pullList() {
        return getHomeListTaskList(EmailTypeEnum.PULL.getType());
    }

    private List<HomeListTaskVO> getHomeListTaskList(Integer type) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        Task paramTask = new Task();
        paramTask.setCreateId(userId);
        paramTask.setDelFlag("0");
        List<Task> taskList = taskMapper.selectTaskList(paramTask);
        if (taskList == null || taskList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> taskIds = taskList.stream().map(Task::getId).collect(Collectors.toList());
        Map<Long, Integer> idMailQuantityMap = taskEmailService.getEmailQuantityByIds(taskIds, type);
        if (idMailQuantityMap == null) {
            idMailQuantityMap = new HashMap<>();
        }

        List<HomeListTaskVO> homeListTaskVOList = new ArrayList<>(taskIds.size());

        Map<Long, Integer> finalIdMailQuantityMap = idMailQuantityMap;
        taskList.stream().forEach(task -> {
            HomeListTaskVO homeListTaskVO = new HomeListTaskVO();
            BeanUtils.copyProperties(task, homeListTaskVO);

            Integer mailQuantity = finalIdMailQuantityMap.get(task.getId());
            homeListTaskVO.setMailQuantity(mailQuantity == null ? 0 : mailQuantity != null ? mailQuantity : 0);

            homeListTaskVOList.add(homeListTaskVO);
        });

        return homeListTaskVOList;
    }

    @Override
    public boolean existById(Long id, Long createId) {
        int count = taskMapper.countById(id, createId);
        return count > 0 ? true : false;
    }

    @Override
    public List<Long> listIdByUserId(Long userId) {
        return taskMapper.listIdByUserId(userId);
    }

    /**
     * 新增邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    @Override
    public Boolean insertTask(Task task)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 是否存在邮箱账号
        if (isExistAccount(task.getAccount(), userId)) {
            throw new ServiceException("邮箱账号已存在");
        }

        // 获取协议连接配置信息
        List<Pair<ProtocolTypeEnum, MailConnCfg>> protocolConnCfgPairList = getProtocolConnCfg(task);

        boolean flag = false;
        MailConnCfg mailConnCfg = getMailConnCfg(task);
        int protocolType = 0;
        for (Pair<ProtocolTypeEnum, MailConnCfg> protocolConnCfgPair : protocolConnCfgPairList) {
            ProtocolTypeEnum protocolTypeEnum = protocolConnCfgPair.getFirst();
            MailConnCfg receivingServerInfo = protocolConnCfgPair.getSecond();

            // 邮箱连接
            mailConnCfg.setHost(receivingServerInfo.getHost());
            mailConnCfg.setPort(receivingServerInfo.getPort());
            mailConnCfg.setSsl(receivingServerInfo.isSsl());

            try {
                mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
                flag = true;
                protocolType = protocolTypeEnum.getType();
                break;
            } catch (MailPlusException e) {
                log.error("邮箱连接失败{}，\n" +
                        "配置信息为{}", e, mailConnCfg);
            }
        }

        if (!flag) {
            throw new ServiceException("邮箱连接失败");
        }

        task.setProtocolType(protocolType);
        task.setReceivingServer(mailConnCfg.getHost());
        task.setReceivingPort(mailConnCfg.getPort());
        task.setReceivingSslFlag(mailConnCfg.isSsl());
        task.setCreateId(loginUser.getUserId());
        task.setCreateBy(loginUser.getUsername());
        task.setCreateTime(DateUtils.getNowDate());
        task.setUpdateId(loginUser.getUserId());
        task.setUpdateBy(loginUser.getUsername());
        task.setUpdateTime(DateUtils.getNowDate());
        task.setConnStatus(ConnStatusEnum.NORMAL.getType());
        // 插入邮箱任务
        taskMapper.insertTask(task);

        // 获取邮箱邮件
        new Thread(() -> {
            pullEmail(task);
        }).start();

        return true;
    }

    /**
     * 获取协议连接配置信息
     * @return
     */
    private List<Pair<ProtocolTypeEnum, MailConnCfg>> getProtocolConnCfg(Task task) {
        List<Pair<ProtocolTypeEnum, MailConnCfg>> protocolConnCfgPairList = new ArrayList<>();
        if (task.getProtocolType() != null) {
            ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
            if (protocolTypeEnum == null) {
                throw new ServiceException("暂不支持该协议类型");
            }

            task.setOutgoingSslFlag(Optional.ofNullable(task.getOutgoingSslFlag()).orElse(false));

            MailConnCfg mailConnCfg = MailConnCfg.builder().host(task.getReceivingServer()).port(task.getReceivingPort()).ssl(Optional.ofNullable(task.getReceivingSslFlag()).orElse(false)).build();
            protocolConnCfgPairList.add(Pair.of(protocolTypeEnum, mailConnCfg));
        } else {
            // 没有选择协议类型，则获取服务器信息
            String domain = task.getAccount().substring(task.getAccount().indexOf("@") + 1);
            Host host = hostService.selectHostByDomain(domain);
            if (host == null) {
                throw new ServiceException("暂不支持该邮箱类型");
            }

            task.setOutgoingServer(host.getSmtpHost());
            task.setOutgoingPort(host.getSmtpPort());
            task.setOutgoingSslFlag(Optional.ofNullable(host.getSmtpSsl()).orElse(false));

            if (StringUtils.isNotBlank(host.getImapHost()) && host.getImapPort() != null) {
                MailConnCfg imapMailConnCfg = MailConnCfg.builder().host(host.getImapHost()).port(host.getImapPort()).ssl(Optional.ofNullable(host.getImapSsl()).orElse(false)).build();
                protocolConnCfgPairList.add(Pair.of(ProtocolTypeEnum.IMAP, imapMailConnCfg));
            }

            if (StringUtils.isNotBlank(host.getPopHost()) && host.getPopPort() != null) {
                MailConnCfg pop3MailConnCfg = MailConnCfg.builder().host(host.getPopHost()).port(host.getPopPort()).ssl(Optional.ofNullable(host.getPopSsl()).orElse(false)).build();
                protocolConnCfgPairList.add(Pair.of(ProtocolTypeEnum.POP3, pop3MailConnCfg));
            }

            if (protocolConnCfgPairList.isEmpty()) {
                throw new ServiceException("暂不支持该邮箱类型");
            }
        }

        return protocolConnCfgPairList;
    }

    /**
     * 拉去邮件
     * @param task
     */
    @Override
    public Pair<Integer, String> pullEmail(Task task) {
        Integer connStatus = 2;
        // 获取邮箱协议
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
        if (protocolTypeEnum == null) {
            log.info("暂不支持该协议类型");
            return Pair.of(connStatus, "暂不支持该协议类型");
        }

        // 邮箱连接
        MailConnCfg mailConnCfg = getMailConnCfg(task);
        MailConn mailConn = null;
        try {
            mailConn = mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
        } catch (MailPlusException e) {
            log.error("邮箱连接失败");
            return Pair.of(connStatus, e.getMessage());
        }

        // 使用redis加个锁，防止拉取到重复的邮件
        Long id = task.getId();
        String lockKey = CacheConstants.EMAIL_PULL_KEY + id;
        Boolean setIfAbsent = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "", 10, TimeUnit.MINUTES);
        if (!setIfAbsent) {
            log.info("存在任务id为【{}】在拉取邮件，请稍后...", id);
            return null;
        }

        // 查询邮箱配置
        EmailOperateParamBO emailOperateParamBO = getMailboxConfigByCreateId(task.getCreateId());

        try {
            // 查询存在的uid
            List<String> existUidList = taskEmailService.getUidsByTaskId(task.getId());

            List<MailItem> mailItems = mailContext.listAll(protocolTypeEnum, mailConn, existUidList);
            if (mailItems == null || mailItems.size() == 0) {
                return null;
            }

            for (MailItem mailItem : mailItems) {
                try {
                    String newEmailPath = emailPath.concat("/").concat(task.getAccount());
                    String newAttachmentPath = attachmentPath.concat("/").concat(task.getAccount());
                    UniversalMail universalMail = mailContext.parseEmail(protocolTypeEnum, mailItem, newEmailPath, newAttachmentPath);
                    // 保存邮件信息
                    if (universalMail.getSendDate() == null) continue;

                    saveEmailData(task, universalMail, emailOperateParamBO.getTransceiverRuleList());

                    // 外出自动回复处理
                    autoResponseHandler(task, universalMail, emailOperateParamBO);

                } catch (MailPlusException e) {
                    log.error("{}", e);
                }
            }

        } catch (Exception e) {
            log.error("邮件拉取失败，原始错误信息为【{}】", e);
        } finally {
            stringRedisTemplate.delete(lockKey);
        }

        return null;
    }

    /**
     * 外出自动回复处理
     * @param task
     * @param universalMail
     * @param emailOperateParamBO
     */
    private void autoResponseHandler(Task task, UniversalMail universalMail, EmailOperateParamBO emailOperateParamBO) {
        // 查询外出自动回复
        Boolean autoResponseFlag = emailOperateParamBO.getAutoResponseFlag();
        if (Optional.ofNullable(autoResponseFlag).orElse(false)) {
            Date now = DateUtils.getNowDate();
            Date startTime = emailOperateParamBO.getStartTime();
            if (now.compareTo(startTime) < 0) return;

            if (Optional.ofNullable(emailOperateParamBO.getLastDayFlag()).orElse(false) && emailOperateParamBO.getLastDay() != null) {
                Date lastDay = emailOperateParamBO.getLastDay();
                if (now.compareTo(lastDay) > 0) return;
            }

            if (StringUtils.isBlank(emailOperateParamBO.getReContent())) return;

            taskEmailService.autoResponse(task, universalMail.getFromer(), universalMail.getTitle(), emailOperateParamBO.getReContent(), universalMail.getMessageId());
        }
    }


    /**
     * 根据创建者ID获取邮箱配置
     * @param createId
     */
    private EmailOperateParamBO getMailboxConfigByCreateId(Long createId) {
        EmailOperateParamBO emailOperateParamBO = new EmailOperateParamBO();
        if (createId != null) {
            // 查询常规配置
            GeneralVO generalVO = generalMapper.getByCreateId(createId);

            // 查询其它配置
            OtherConfigVO otherConfigVO = otherConfigMapper.getByCreateId(createId);
            // 查询收发件规则
            List<TransceiverRuleVO> transceiverRuleList= transceiverRuleMapper.list(createId);
            transceiverRuleList = transceiverRuleList.stream().filter(transceiverRule -> transceiverRule.getRuleType().intValue() == RuleTypeEnum.PULL.getType()).filter(transceiverRule -> transceiverRule.getStatus().intValue() == 1).collect(Collectors.toList());

            if (generalVO != null) {
                BeanUtils.copyProperties(generalVO, emailOperateParamBO);
            }

            if (otherConfigVO != null) {
                BeanUtils.copyProperties(otherConfigVO, emailOperateParamBO);
            }

            if (transceiverRuleList != null && !transceiverRuleList.isEmpty()) {
                emailOperateParamBO.setTransceiverRuleList(transceiverRuleList);
            }
        }

        return emailOperateParamBO;
    }

    @Override
    public void syncAllTaskEmail() {
        Task taskParam = new Task();
        taskParam.setConnStatus(ConnStatusEnum.NORMAL.getType());
        taskParam.setDelFlag("0");
        List<Task> taskList = taskMapper.selectTaskList(taskParam);
        ThreadPoolExecutor instance = ThreadPoolPullService.getInstance();
        taskList.stream().forEach(task -> {
            instance.execute(() -> {
                Pair<Integer, String> pair = pullEmail(task);
                if (pair != null) {
                    Integer connStatus = pair.getFirst();
                    String connExceptionReason = pair.getSecond();
                    // 更新任务为异常
                    task.setConnStatus(connStatus);
                    task.setConnExceptionReason(connExceptionReason);
                    taskMapper.updateTask(task);
                }
            });
        });
    }

    @Override
    public void sendEmail() {
        Task taskParam = new Task();
        taskParam.setDelFlag("0");
        List<Task> taskList = taskMapper.selectTaskList(taskParam);
        ThreadPoolExecutor instance = ThreadPoolSendService.getInstance();
        taskList.stream().forEach(task -> {
            instance.execute(() -> {
                sendEmail(task);
            });
        });
    }

    @Override
    public List<Long> getTaskIdByUserId() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return listIdByUserId(userId);
    }

    @Override
    public List<HomeListTaskVO> sendList() {
        return getHomeListTaskList(EmailTypeEnum.SEND.getType());
    }

    /**
     * 发送邮件
     * @param task
     */
    private void sendEmail(Task task) {
        // 更新邮件为发送中
        taskEmailService.updateTaskSendEmailStatus(task.getId());

        List<TaskEmail> taskEmailList = new ArrayList<>();
        synchronized (task.getId()) {
            taskEmailList = taskEmailService.selectByUnSentStatus(task.getId());
            if (taskEmailList == null || taskEmailList.isEmpty()) return;
        }

        // 获取随机休眠时间
        int randomSleepTime = getRandomSleepTime(task.getCreateId());
        int finalRandomSleepTime = randomSleepTime;
        taskEmailList.stream().forEach(taskEmail -> {
            if (finalRandomSleepTime > 0) {
                try {
                    Thread.sleep(finalRandomSleepTime);
                } catch (InterruptedException e) {
                    log.error("休眠异常：{}", e);
                }
            }

            taskEmailService.sendEmail(taskEmail);
        });
    }

    /**
     * 获取随机休眠时间
     * @param createId
     * @return
     */
    private int getRandomSleepTime(Long createId) {
        int randomSleepTime = 0;
        if (createId == null) return randomSleepTime;

        try {
            OtherConfigVO otherConfigVO = otherConfigMapper.getByCreateId(createId);
            if (otherConfigVO == null || StringUtils.isBlank(otherConfigVO.getSendingInterval())) return randomSleepTime;

            String sendingInterval = otherConfigVO.getSendingInterval();
            String[] arr = sendingInterval.split("~");
            int start = Integer.parseInt(arr[0]);
            int end = Integer.parseInt(arr[1]);

            Random random = new Random();
            randomSleepTime = random.nextInt(end - start) + start;
        } catch (Exception e) {
            log.error("异常：{}", e);
        }
        return randomSleepTime;
    }

    /**
     * 保存邮件数据
     * @param task
     * @param universalMail
     */
    private void saveEmailData(Task task, UniversalMail universalMail, List<TransceiverRuleVO> transceiverRuleList) {
        TaskEmail taskEmail = new TaskEmail();
        Date nowDate = DateUtils.getNowDate();
        // 邮件
        BeanUtils.copyProperties(universalMail, taskEmail);
        taskEmail.setTaskId(task.getId());
        if (taskEmail.getFolder().equals("已发送")) {
            taskEmail.setType(EmailTypeEnum.SEND.getType());
        } else {
            taskEmail.setType(EmailTypeEnum.PULL.getType());
        }
        taskEmail.setStatus(TaskExecutionStatusEnum.SUCCESS.getStatus());
        taskEmail.setCreateTime(nowDate);
        taskEmail.setUpdateTime(nowDate);
        taskEmailService.insertTaskEmail(taskEmail);
        Long emailId = taskEmail.getId();

        // 邮件内容
        String content = universalMail.getContent();
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailId);
        emailContent.setContent(content);
        emailContent.setCreateTime(nowDate);
        taskEmailContentService.insertTaskEmailContent(emailContent);

        //附件
        List<TaskAttachment> taskAttachmentList = new ArrayList<>();
        List<UniversalAttachment> attachments = universalMail.getAttachments();
        if (attachments != null) {
            for (UniversalAttachment attachment : attachments) {
                TaskAttachment taskAttachment = new TaskAttachment();
                BeanUtils.copyProperties(attachment, taskAttachment);
                taskAttachment.setCreateTime(nowDate);
                taskAttachment.setUpdateTime(nowDate);
                taskAttachmentList.add(taskAttachment);
            }

            if (!taskAttachmentList.isEmpty()) {
                taskAttachmentService.batchInsertTaskAttachment(taskAttachmentList);

                // 批量保存邮件附件关联数据
                List<TaskEmailAttachment> taskEmailAttachmentList = new ArrayList<>();
                taskAttachmentList.stream().forEach(taskAttachment -> {
                    taskEmailAttachmentList.add(TaskEmailAttachment.builder().emailId(emailId).attachmentId(taskAttachment.getId()).build());
                });

                taskEmailAttachmentService.batchInsertTaskEmailAttachment(taskEmailAttachmentList);
            }
        }

        // 黑名单处理
     //   blacklistHandler(taskEmail, task.getCreateId());


        // 收件规则处理
        taskEmailService.transceiverRuleHandler(taskEmail, content, transceiverRuleList);
    }

    /**
     * 黑名单处理 todo 待测试
     * @param createId
     */
    private void blacklistHandler(TaskEmail taskEmail, Long createId) {
        // 黑名单列表
        String fromer = taskEmail.getFromer();
        boolean isGone = false;
        Integer emailCount = blacklistMapper.countBlacklistListByEmail(createId, fromer);
        if (emailCount.intValue() > 0) {
            isGone = true;
        }

        if (isGone) {
            Integer domainCount = blacklistMapper.countBlacklistListByDomain(createId, fromer);
            if (domainCount.intValue() > 0) {
                isGone = true;
            }
        }

        if (isGone) {
            taskEmail.setSpamFlag(true);
            taskEmailService.updateTaskEmail(taskEmail);
        }
    }


    @Override
    public void saveEmailData(Long taskId, Long folderId, Integer type, UniversalMail universalMail) {
        TaskEmail taskEmail = new TaskEmail();
        // 邮件
        BeanUtils.copyProperties(universalMail, taskEmail);
        taskEmail.setTaskId(taskId);
        taskEmail.setFolderId(folderId);
        taskEmail.setType(type);
        taskEmail.setStatus(TaskExecutionStatusEnum.SUCCESS.getStatus());
        taskEmail.setCreateTime(DateUtils.getNowDate());
        taskEmailService.insertTaskEmail(taskEmail);
        Long emailId = taskEmail.getId();

        // 邮件内容
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailId);
        emailContent.setContent(universalMail.getContent());
        emailContent.setCreateTime(DateUtils.getNowDate());
        taskEmailContentService.insertTaskEmailContent(emailContent);

        //附件
        List<TaskAttachment> taskAttachmentList = new ArrayList<>();
        List<UniversalAttachment> attachments = universalMail.getAttachments();
        if (attachments != null) {
            for (UniversalAttachment attachment : attachments) {
                TaskAttachment taskAttachment = new TaskAttachment();
                BeanUtils.copyProperties(attachment, taskAttachment);
                taskAttachment.setCreateTime(DateUtils.getNowDate());
                taskAttachmentList.add(taskAttachment);
            }

            if (!taskAttachmentList.isEmpty()) {
                taskAttachmentService.batchInsertTaskAttachment(taskAttachmentList);

                // 批量保存邮件附件关联数据
                List<TaskEmailAttachment> taskEmailAttachmentList = new ArrayList<>();
                taskAttachmentList.stream().forEach(taskAttachment -> {
                    taskEmailAttachmentList.add(TaskEmailAttachment.builder().emailId(emailId).attachmentId(taskAttachment.getId()).build());
                });

                taskEmailAttachmentService.batchInsertTaskEmailAttachment(taskEmailAttachmentList);
            }
        }
    }

    /**
     * 邮箱测试
     */
    @Override
    public void testEmail() {
        // 查询所有的任务
        Task taskParam = new Task();
        taskParam.setDelFlag("0");
        List<Task> taskList = taskMapper.selectTaskList(taskParam);
        taskList.stream().forEach(task -> {
            if (task.getCreateId() != null) {
                OtherConfigVO otherConfigVO = otherConfigMapper.getByCreateId(task.getCreateId());
                if (otherConfigVO != null && otherConfigVO.getAbnormalMailboxDetection() != null && otherConfigVO.getAbnormalMailboxDetection().intValue() == 1) {
                    testEmail(task);
                }
            }
        });
    }

    /**
     * 获取邮箱配置
     * @param task
     * @return
     */
    private MailConnCfg getMailConnCfg(Task task) {
        MailConnCfg mailConnCfg = new MailConnCfg();
        mailConnCfg.setEmail(task.getAccount());
        mailConnCfg.setPassword(task.getPassword());
        mailConnCfg.setHost(task.getReceivingServer());
        mailConnCfg.setPort(task.getReceivingPort());
        mailConnCfg.setSsl(Optional.ofNullable(task.getReceivingSslFlag()).orElse(false));

        Boolean customProxyFlag = task.getCustomProxyFlag();
        if (Optional.ofNullable(customProxyFlag).orElse(false)) return mailConnCfg;

        Integer proxyServerType = task.getProxyServerType();
        ProxyTypeEnum proxyTypeEnum = ProxyTypeEnum.getByType(proxyServerType);
        if (proxyTypeEnum == null) return mailConnCfg;

        mailConnCfg.setProxyType(proxyTypeEnum);
        switch (proxyTypeEnum) {
            case HTTP:
                mailConnCfg.setProxyHost(task.getProxyServer());
                mailConnCfg.setProxyPort(task.getProxyPort());
            case SOCKS:
                mailConnCfg.setSocksProxyHost(task.getProxyServer());
                mailConnCfg.setSocksProxyPort(task.getProxyPort());
            default:
                mailConnCfg.setProxyUsername(task.getProxyUsername());
                mailConnCfg.setProxyPassword(task.getProxyPassword());
        }

        return mailConnCfg;
    }

    /**
     * 是否存在邮箱账号
     * @param account
     */
    private boolean isExistAccount(String account, Long userId) {
        int count = taskMapper.countAccount(account, userId);
        return count > 0 ? true : false;
    }

    /**
     * 修改邮箱任务
     * 
     * @param editTaskDTO 邮箱任务
     * @return 结果
     */
    @Override
    public int updateTask(EditTaskDTO editTaskDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Task task = taskMapper.getTaskById(editTaskDTO.getId(), userId);
        BeanUtils.copyProperties(editTaskDTO, task);

        MailConnCfg mailConnCfg = getMailConnCfg(task);
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());

        try {
            mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
        } catch (MailPlusException e) {
            log.error("邮箱连接失败{}，\n" +
                    "配置信息为{}", e, mailConnCfg);
            throw new ServiceException("邮箱连接失败");
        }

        // 根据ID查询邮箱任务
        task.setUpdateId(userId);
        task.setUpdateBy(username);
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除邮箱任务信息
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }

    /**
     * 邮箱检测
     * @param id
     * @return
     */
    @Override
    public TestTaskVO testTask(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        Task task = taskMapper.getTaskById(id, userId);
        if (task == null) {
            log.info("任务为空，id为【{}】", id);
            throw new ServiceException("任务为空");
        }

        return testEmail(task);
    }

    /**
     * 邮箱检测
     * @param task
     * @return
     */
    private TestTaskVO testEmail(Task task) {
        // 获取邮箱协议
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
        if (protocolTypeEnum == null) {
            log.info("暂不支持该协议类型");
            throw new ServiceException("暂不支持该协议类型");
        }

        int connStatus = 1;
        String connExceptionReason = null;
        // 邮箱连接
        MailConnCfg mailConnCfg = getMailConnCfg(task);
        try {
            mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
        } catch (Exception e) {
            log.error("邮箱连接失败");
            connStatus = 2;
            connExceptionReason = e.getMessage();
        }

        task.setConnStatus(connStatus);
        task.setConnExceptionReason(connExceptionReason);
        taskMapper.updateTask(task);

        return TestTaskVO.builder().connStatus(connStatus).connExceptionReason(connExceptionReason).build();
    }
}
