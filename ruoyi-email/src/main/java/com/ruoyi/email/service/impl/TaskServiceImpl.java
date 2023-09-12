package com.ruoyi.email.service.impl;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.ProxyTypeEnum;
import com.ruoyi.common.enums.email.ConnStatusEnum;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.enums.email.TaskExecutionStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.*;
import com.ruoyi.email.domain.dto.task.EditTaskDTO;
import com.ruoyi.email.domain.vo.task.HomeListTaskVO;
import com.ruoyi.email.domain.vo.task.ListTaskVO;
import com.ruoyi.email.domain.vo.task.TestTaskVO;
import com.ruoyi.email.service.*;
import com.ruoyi.email.service.handler.email.*;
import com.ruoyi.email.util.ThreadPoolPullService;
import com.ruoyi.email.util.ThreadPoolSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskMapper;

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
    private ITaskEmailPullService taskEmailPullService;

    @Resource
    private ITaskEmailContentService taskEmailContentService;

    @Resource
    private ITaskEmailAttachmentService taskEmailAttachmentService;

    @Resource
    private ITaskEmailPullService taskEmailPullServiceImpl;

    @Resource
    private ITaskEmailSendService taskEmailSendService;

    @Value("${email.pull.path}")
    private String emailPath;

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
    public List<ListTaskVO> listTask()
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
        Map<Long, Integer> idMailQuantityMap = taskEmailPullServiceImpl.getPullEmailQuantityByIds(taskIds);
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

        // 没有选择协议类型，则获取服务器信息
        String domain = task.getAccount().substring(task.getAccount().indexOf("@") + 1);
        Host host = hostService.selectHostByDomain(domain);
        if (host == null) {
            throw new ServiceException("暂不支持该邮箱类型");
        }

        // 获取协议连接配置信息
        List<Pair<ProtocolTypeEnum, MailConnCfg>> protocolConnCfgPairList = getProtocolConnCfg(task, host);

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

        String outgoingServer = StringUtils.isNotBlank(task.getOutgoingServer()) ? task.getOutgoingServer() : host.getSmtpHost();
        Integer outgoingPort = task.getOutgoingPort() != null ? task.getOutgoingPort() : host.getSmtpPort();
        Boolean outgoingSslFlag = task.getOutgoingSslFlag() != null ? task.getOutgoingSslFlag() : Optional.ofNullable(host.getSmtpSsl()).orElse(false);

        task.setProtocolType(protocolType);
        task.setReceivingServer(mailConnCfg.getHost());
        task.setReceivingPort(mailConnCfg.getPort());
        task.setReceivingSslFlag(mailConnCfg.isSsl());
        task.setOutgoingServer(outgoingServer);
        task.setOutgoingPort(outgoingPort);
        task.setOutgoingSslFlag(outgoingSslFlag);
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
    private List<Pair<ProtocolTypeEnum, MailConnCfg>> getProtocolConnCfg(Task task, Host host) {
        List<Pair<ProtocolTypeEnum, MailConnCfg>> protocolConnCfgPairList = new ArrayList<>();
        if (task.getProtocolType() != null) {
            ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
            if (protocolTypeEnum == null) {
                throw new ServiceException("暂不支持该协议类型");
            }

            MailConnCfg mailConnCfg = MailConnCfg.builder().host(task.getReceivingServer()).port(task.getReceivingPort()).ssl(Optional.ofNullable(task.getReceivingSslFlag()).orElse(false)).build();
            protocolConnCfgPairList.add(Pair.of(protocolTypeEnum, mailConnCfg));
        } else {


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

        try {
            // 查询存在的uid
            List<String> existUidList = taskEmailPullService.getUidsByTaskId(task.getId());

            List<MailItem> mailItems = mailContext.listAll(protocolTypeEnum, mailConn, existUidList);
            if (mailItems == null || mailItems.size() == 0) {
                return null;
            }

            for (MailItem mailItem : mailItems) {
                UniversalMail universalMail = mailContext.parseEmail(protocolTypeEnum, mailItem, emailPath);
                // 保存邮件信息
                if (universalMail.getSendDate() != null) {
                    saveEmailData(task.getId(), universalMail);
                }
            }

        } catch (Exception e) {
            log.error("邮件拉取失败，原始错误信息为【{}】", e);
        }

        return null;
    }

    @Override
    public void syncAllTaskEmail() {
        List<Task> taskList = taskMapper.selectTaskList(new Task());
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
        List<Task> taskList = taskMapper.selectTaskList(new Task());
        ThreadPoolExecutor instance = ThreadPoolSendService.getInstance();
        taskList.stream().forEach(task -> {
            instance.execute(() -> {
                sendEmail(task.getId());
            });
        });
    }

    @Override
    public List<Long> getTaskIdByUserId() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return listIdByUserId(userId);
    }

    /**
     * 发送邮件
     * @param id
     */
    private void sendEmail(Long id) {
        TaskEmailSend taskEmailSendParam = new TaskEmailSend();
        taskEmailSendParam.setTaskId(id);
        taskEmailSendParam.setStatus(TaskExecutionStatusEnum.IN_PROGRESS.getStatus());
        taskEmailSendParam.setDelayedTxFlag(false);
        List<TaskEmailSend> taskEmailSendList = taskEmailSendService.selectTaskEmailSendList(taskEmailSendParam);

        taskEmailSendList.stream().forEach(taskEmailSend -> {
            taskEmailSendService.sendEmail(taskEmailSend);
        });
        
    }

    /**
     * 保存邮件数据
     * @param taskId
     * @param universalMail
     */
    private void saveEmailData(Long taskId, UniversalMail universalMail) {
        TaskEmailPull taskEmailPull = new TaskEmailPull();

        // 邮件
        BeanUtils.copyProperties(universalMail, taskEmailPull);
        taskEmailPull.setTaskId(taskId);
        taskEmailPull.setCreateTime(DateUtils.getNowDate());
        taskEmailPullService.insertTaskEmailPull(taskEmailPull);
        Long emailId = taskEmailPull.getId();

        // 邮件内容
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailId);
        emailContent.setType(EmailTypeEnum.PULL.getType());
        emailContent.setContent(universalMail.getContent());
        emailContent.setCreateTime(DateUtils.getNowDate());
        taskEmailContentService.insertTaskEmailContent(emailContent);

        //邮件附件
        List<TaskEmailAttachment> emailAttachments = new ArrayList<>();
        List<UniversalAttachment> attachments = universalMail.getAttachments();
        if (attachments != null) {
            for (UniversalAttachment attachment : attachments) {
                TaskEmailAttachment emailAttachment = new TaskEmailAttachment();
                BeanUtils.copyProperties(attachment, emailAttachment);
                emailAttachment.setEmailId(emailId);
                emailAttachment.setType(EmailTypeEnum.PULL.getType());
                emailAttachment.setCreateTime(DateUtils.getNowDate());
                emailAttachments.add(emailAttachment);
            }

            taskEmailAttachmentService.batchInsertTaskEmailAttachment(emailAttachments);
        }
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
