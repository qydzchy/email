package com.ruoyi.email.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.AndOrEnum;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.RuleTypeEnum;
import com.ruoyi.common.enums.email.TaskExecutionStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.email.domain.*;
import com.ruoyi.email.domain.bo.*;
import com.ruoyi.email.domain.dto.email.EmailQuickReplyDTO;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.*;
import com.ruoyi.email.mapper.*;
import com.ruoyi.email.service.*;
import com.ruoyi.email.service.handler.email.column.EmailColumnContext;
import com.ruoyi.email.util.Constant;
import com.ruoyi.email.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;


/**
 * 邮件Service业务层处理
 *
 * @author tangJM.
 * @date 2023-09-12
 */
@Slf4j
@Service
public class TaskEmailServiceImpl implements ITaskEmailService {

    // 邮件追踪
    private static final String HTML_IMG_TRACE = "<img src='%s/image/path/to/tracker.png?id=%s' style='display:none'>";

    @Resource
    private TaskEmailMapper taskEmailMapper;
    @Resource
    private OtherConfigMapper otherConfigMapper;
    @Resource
    private ITaskEmailContentService taskEmailContentService;
    @Resource
    private ITaskEmailAttachmentService taskEmailAttachmentService;
    @Resource
    private ITaskAttachmentService taskAttachmentService;
    @Resource
    private ITaskEmailLabelService taskEmailLabelService;
    @Resource
    private ILabelService labelService;
    @Resource
    private EmailColumnContext emailColumnContext;
    @Resource
    private TransceiverRuleMapper transceiverRuleMapper;
    @Resource
    private TaskEmailLabelMapper taskEmailLabelMapper;
    @Resource
    private LabelMapper labelMapper;
    @Resource
    private TaskMapper taskMapper;

    @Lazy
    @Resource
    private ITaskService taskService;

    @Value("${email.trace.server}")
    private String traceServer;

    /**
     * 查询邮件
     *
     * @param id 邮件主键
     * @return 邮件
     */
    @Override
    public TaskEmail selectTaskEmailById(Long id) {
        return taskEmailMapper.selectTaskEmailById(id);
    }

    /**
     * 查询邮件列表
     *
     * @param taskEmail 邮件
     * @return 邮件
     */
    @Override
    public List<TaskEmail> selectTaskEmailList(TaskEmail taskEmail) {
        return taskEmailMapper.selectTaskEmailList(taskEmail);
    }

    /**
     * 新增邮件
     *
     * @param taskEmail 邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmail(TaskEmail taskEmail) {
        taskEmail.setCreateTime(DateUtils.getNowDate());
        return taskEmailMapper.insertTaskEmail(taskEmail);
    }

    /**
     * 修改邮件
     *
     * @param taskEmail 邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmail(TaskEmail taskEmail) {
        taskEmail.setUpdateTime(DateUtils.getNowDate());
        return taskEmailMapper.updateTaskEmail(taskEmail);
    }

    /**
     * 批量删除邮件
     *
     * @param ids 需要删除的邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailByIds(Long[] ids) {
        return taskEmailMapper.deleteTaskEmailByIds(ids);
    }

    /**
     * 删除邮件信息
     *
     * @param id 邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailById(Long id) {
        return taskEmailMapper.deleteTaskEmailById(id);
    }

    /**
     * 查询邮件列表（首页）
     *
     * @param taskIdList
     * @param type
     * @param readFlag
     * @param pendingFlag
     * @param delFlag
     * @param draftsFlag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Pair<Integer, List<Map<String, List<EmailListVO>>>> list(List<Long> taskIdList, Integer type, Boolean readFlag, Boolean pendingFlag, Boolean spamFlag, String delFlag, Boolean draftsFlag, Boolean traceFlag, Boolean fixedFlag, Boolean attachmentFlag, Boolean customerFlag, Long folderId, Long labelId, Integer pageNum, Integer pageSize) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        if (taskIdList.isEmpty()) {
            return Pair.of(0, new ArrayList<>());
        }

        List<Integer> statusList = Arrays.asList(TaskExecutionStatusEnum.SUCCESS.getStatus());
        if (Optional.ofNullable(pendingFlag).orElse(false)) {
            statusList = Arrays.asList(TaskExecutionStatusEnum.SUCCESS.getStatus(), TaskExecutionStatusEnum.NOT_STARTED.getStatus(), TaskExecutionStatusEnum.IN_PROGRESS.getStatus(), TaskExecutionStatusEnum.FAILURE.getStatus());
        } else if (Optional.ofNullable(draftsFlag).orElse(false)) {
            statusList = Arrays.asList(TaskExecutionStatusEnum.NOT_STARTED.getStatus(), TaskExecutionStatusEnum.IN_PROGRESS.getStatus(), TaskExecutionStatusEnum.FAILURE.getStatus());
        }

        int count = taskEmailMapper.count(taskIdList, type, readFlag, pendingFlag, spamFlag, delFlag, traceFlag, fixedFlag, attachmentFlag, customerFlag, folderId, labelId, statusList, userId);
        if (count <= 0) {
            return Pair.of(0, new ArrayList<>());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<EmailListVO> emailListVOList = taskEmailMapper.selectTaskEmailPage(taskIdList, type, readFlag, pendingFlag, spamFlag, delFlag, traceFlag, fixedFlag, attachmentFlag, customerFlag, folderId, labelId, statusList, userId, offset, limit);
        if (emailListVOList == null || emailListVOList.isEmpty()) {
            return Pair.of(count, new ArrayList<>());
        }

        List<Long> ids = emailListVOList.stream().map(emailListVO -> emailListVO.getId()).collect(Collectors.toList());
        // 查询邮件附件信息
        List<EmailAttachmentBO> emailAttachmentBOList = taskAttachmentService.listByEmailIds(ids);
        if (emailAttachmentBOList == null) emailAttachmentBOList = Collections.emptyList();
        Map<Long, List<EmailAttachmentBO>> attachmentGroupMap = emailAttachmentBOList.stream().collect(Collectors.groupingBy(emailAttachment -> emailAttachment.getEmailId()));

        // 查询邮件标签信息
        List<EmailLabelBO> emailLabelBOList = labelService.listByEmailIds(ids);
        if (emailLabelBOList == null) emailLabelBOList = Collections.emptyList();
        Map<Long, List<EmailLabelBO>> labelGroupMap = emailLabelBOList.stream().collect(Collectors.groupingBy(emailLabel -> emailLabel.getEmailId()));

        emailListVOList.stream().forEach(emailListVO -> {
            Long id = emailListVO.getId();
            if (attachmentGroupMap.containsKey(id)) {
                List<EmailAttachmentBO> emailAttachmentGroupList = attachmentGroupMap.get(id);
                emailListVO.setEmailAttachmentList(emailAttachmentGroupList);
            } else {
                emailListVO.setEmailAttachmentList(Collections.emptyList());
            }

            if (labelGroupMap.containsKey(id)) {
                List<EmailLabelBO> emailLabelGroupList = labelGroupMap.get(id);
                emailListVO.setEmailLabelList(emailLabelGroupList);
            } else {
                emailListVO.setEmailLabelList(Collections.emptyList());
            }
        });

        Map<String, List<EmailListVO>> data = new LinkedHashMap<>();
        emailListVOList.stream().forEach(emailListVO -> {
            Date sendDate = emailListVO.getSendDate();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());
            String dynamicLabel = getDynamicLabel(localDateTime);

            if (data.containsKey(dynamicLabel)) {
                data.get(dynamicLabel).add(emailListVO);
            } else {
                data.put(dynamicLabel, new ArrayList<EmailListVO>() {{
                    add(emailListVO);
                }});
            }
        });

        List<Map<String, List<EmailListVO>>> dataList = new ArrayList<>();
        data.forEach((key, value) -> {
            dataList.add(new HashMap<String, List<EmailListVO>>() {{
                put(key, value);
            }});
        });

        return Pair.of(count, dataList);
    }

    /**
     * 保存-（保存）
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long save(EmailSendSaveDTO dto) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = new Date();

        String inReplyTo = null;
        String reference = "";
        if (dto.getPullEmailId() != null) {
            TaskEmail taskEmail = taskEmailMapper.selectTaskEmailById(dto.getPullEmailId());
            if (taskEmail == null) throw new ServiceException();
            inReplyTo = taskEmail.getMessageId();
            if (taskEmail.getReference() != null) {
                reference = taskEmail.getReference() + " ";
            }

            reference += taskEmail.getMessageId();
        }

        TaskEmail taskEmail = new TaskEmail();
        BeanUtils.copyProperties(dto, taskEmail);
        Date pendingTime = getPendingTime(dto.getPendingTime(), userId);
        taskEmail.setPendingTime(pendingTime);
        taskEmail.setUid(IdUtils.fastSimpleUUID());
        taskEmail.setType(EmailTypeEnum.SEND.getType());
        taskEmail.setFolder("INBOX");
        taskEmail.setSendDate(pendingTime);
        taskEmail.setStatus(TaskExecutionStatusEnum.NOT_STARTED.getStatus());
        taskEmail.setInReplyTo(inReplyTo);
        taskEmail.setReference(reference);
        taskEmail.setCreateId(userId);
        taskEmail.setCreateBy(username);
        taskEmail.setCreateTime(now);
        taskEmail.setUpdateId(userId);
        taskEmail.setUpdateBy(username);
        taskEmail.setUpdateTime(now);
        taskEmailMapper.insertTaskEmail(taskEmail);

        Long emailId = taskEmail.getId();
        // 保存邮件内容
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailId);
        emailContent.setContent(dto.getContent());
        emailContent.setCreateId(userId);
        emailContent.setCreateBy(username);
        emailContent.setCreateTime(now);
        emailContent.setUpdateId(userId);
        emailContent.setUpdateBy(username);
        emailContent.setUpdateTime(now);
        taskEmailContentService.insertTaskEmailContent(emailContent);

        // 删除邮件附件关联关系
        taskEmailAttachmentService.deleteByEmailId(emailId);

        // 更新邮件附件的emailId
        List<Long> attachmentIdList = dto.getAttachmentIdList();
        List<TaskEmailAttachment> taskEmailAttachmentList = new ArrayList<>();
        if (attachmentIdList != null) {
            attachmentIdList.stream().forEach(attachmentId -> {
                taskEmailAttachmentList.add(TaskEmailAttachment.builder().emailId(emailId).attachmentId(attachmentId).build());
            });
        }

        if (!taskEmailAttachmentList.isEmpty()) {
            taskEmailAttachmentService.batchInsertTaskEmailAttachment(taskEmailAttachmentList);
        }

        return emailId;
    }

    /**
     * 获取定时发送时间
     */
    private Date getPendingTime(Date pendingTime, Long userId) {
        Date sendTime = pendingTime != null ? pendingTime : new Date();
        // 查询其它配置
        OtherConfigVO otherConfigVO = otherConfigMapper.getByCreateId(userId);
        if (otherConfigVO == null || otherConfigVO.getDelayedMailDelivery() == null) return sendTime;

        Integer delayedMailDelivery = otherConfigVO.getDelayedMailDelivery();
        // 邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sendTime);
        switch (delayedMailDelivery) {
            case 1:
                break;
            case 2:
                calendar.add(Calendar.SECOND, 30);
                break;
            case 3:
                calendar.add(Calendar.SECOND, 60);
                break;
            case 4:
                calendar.add(Calendar.SECOND, 120);
                break;
            case 5:
                calendar.add(Calendar.SECOND, 300);
                break;
        }

        sendTime = calendar.getTime();
        return sendTime;
    }

    /**
     * 自动回复
     */
    @Override
    public void autoResponse(Task task, String fromer, String title, String reContent, String messageId) {
        // 查询是否在4天内已经回复过邮件
        if (checkRepliedWithinFourDays(fromer, task.getAccount(), task.getCreateId())) return;

        Date now = DateUtils.getNowDate();
        Long userId = task.getCreateId();
        String username = task.getCreateBy();

        JSONArray jsonA = new JSONArray();
        JSONObject jsonO = new JSONObject();
        jsonO.put("name", fromer);
        jsonO.put("email", fromer);
        jsonA.add(jsonO);

        String receiver = JSONObject.toJSONString(jsonA);
        TaskEmail taskEmail = new TaskEmail();
        taskEmail.setUid(Constant.AUTO_RESPONSE_PREFIX + IdUtils.fastSimpleUUID());
        taskEmail.setTaskId(task.getId());
        taskEmail.setFromer(task.getAccount());
        taskEmail.setReceiver(receiver);
        taskEmail.setTitle("Re: " + title);
        taskEmail.setSendDate(new Date());
        taskEmail.setFolder("INBOX");
        taskEmail.setFolderId(-1L);
        taskEmail.setType(EmailTypeEnum.SEND.getType());
        taskEmail.setStatus(TaskExecutionStatusEnum.IN_PROGRESS.getStatus());
        taskEmail.setCreateId(userId);
        taskEmail.setCreateBy(username);
        taskEmail.setCreateTime(now);
        taskEmail.setUpdateId(userId);
        taskEmail.setUpdateBy(username);
        taskEmail.setUpdateTime(now);
        taskEmail.setInReplyTo(messageId);
        taskEmail.setDelayedTxFlag(false);
        taskEmail.setRecipientLocalTime(now);

        String reference = "";
        if (taskEmail.getReference() != null) {
            reference = taskEmail.getReference() + " ";
        }

        reference += taskEmail.getMessageId();
        taskEmail.setReference(reference);

        taskEmailMapper.insertTaskEmail(taskEmail);

        TaskEmailContent taskEmailContent = new TaskEmailContent();
        taskEmailContent.setEmailId(taskEmail.getId());
        taskEmailContent.setContent(reContent);
        taskEmailContentService.insertTaskEmailContent(taskEmailContent);
    }

    /**
     * 邮件发送-（写信）
     *
     * @param id
     * @return
     */
    @Override
    public boolean send(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        TaskEmail taskEmail = taskEmailMapper.getTaskEmailById(id, userId);
        if (taskEmail == null) throw new ServiceException();

        taskEmail.setStatus(TaskExecutionStatusEnum.IN_PROGRESS.getStatus());
        taskEmail.setUpdateId(userId);
        taskEmail.setUpdateBy(username);
        taskEmail.setUpdateTime(new Date());

        taskEmailMapper.updateTaskEmail(taskEmail);
        return true;
    }

    @Override
    public boolean fixed(Long id, Boolean fixedFlag) {
        return taskEmailMapper.updateFixedFlag(id, fixedFlag);
    }

    @Override
    @Transactional
    public boolean quickReply(EmailQuickReplyDTO emailQuickReplyDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        TaskEmail pullTaskEmail = taskEmailMapper.selectTaskEmailById(emailQuickReplyDTO.getId());
        if (pullTaskEmail == null) throw new ServiceException();

        String username = loginUser.getUsername();
        Date now = new Date();

        // 查询邮箱信息
        Task task = taskService.selectTaskById(pullTaskEmail.getTaskId());
        if (task == null) {
            throw new ServiceException();
        }

        String historyContent = "";
        // 查询邮件内容
        TaskEmailContent historyTaskEmailContent = taskEmailContentService.selectTaskEmailContentByEmailId(pullTaskEmail.getId());
        if (historyTaskEmailContent != null) {
            historyContent = historyTaskEmailContent.getContent();
        }

        JSONArray jsonA = new JSONArray();
        JSONObject jsonO = new JSONObject();
        jsonO.put("name", pullTaskEmail.getFromer());
        jsonO.put("email", pullTaskEmail.getFromer());
        jsonA.add(jsonO);

        String receiver = JSONObject.toJSONString(jsonA);
        TaskEmail taskEmail = new TaskEmail();
        taskEmail.setUid(IdUtils.fastSimpleUUID());
        taskEmail.setTaskId(pullTaskEmail.getTaskId());
        taskEmail.setFromer(task.getAccount());
        taskEmail.setReceiver(receiver);
        taskEmail.setTitle("Re: " + pullTaskEmail.getTitle());
        taskEmail.setSendDate(new Date());
        taskEmail.setType(EmailTypeEnum.SEND.getType());
        taskEmail.setStatus(TaskExecutionStatusEnum.IN_PROGRESS.getStatus());
        taskEmail.setCreateId(userId);
        taskEmail.setCreateBy(username);
        taskEmail.setCreateTime(now);
        taskEmail.setUpdateId(userId);
        taskEmail.setUpdateBy(username);
        taskEmail.setUpdateTime(now);
        taskEmail.setInReplyTo(pullTaskEmail.getMessageId());
        String reference = "";
        if (taskEmail.getReference() != null) {
            reference = taskEmail.getReference() + " ";
        }

        reference += taskEmail.getMessageId();
        taskEmail.setReference(reference);

        taskEmailMapper.insertTaskEmail(taskEmail);

        TaskEmailContent taskEmailContent = new TaskEmailContent();
        taskEmailContent.setEmailId(taskEmail.getId());

        String replyContent = emailQuickReplyDTO.getContent();
        StringBuilder content = new StringBuilder();
        content.append(replyContent);
        content.append("<div style=\"font-size: 12px;font-family: Arial Narrow,serif;padding:2px 0 2px 0;\">\n" +
                " ------------------&nbsp;Original&nbsp;------------------\n" +
                "</div>");

        String from = pullTaskEmail.getFromer();
        List<String> toList = getReceiver(pullTaskEmail.getReceiver());
        List<String> ccList = getReceiver((pullTaskEmail.getCc()));
        String sendTime = DateUtil.formatSendTime(pullTaskEmail.getSendDate());
        String subject = pullTaskEmail.getTitle();

        content.append(formatHistoryEmail(from, sendTime, toList, ccList, subject));
        // 使用邮件内容
        content.append(historyContent);
        taskEmailContent.setContent(content.toString());
        taskEmailContentService.insertTaskEmailContent(taskEmailContent);

        return true;
    }

    private List<String> getReceiver(String email) {
        if (StringUtils.isNotBlank(email)) {
            try {
                JSONArray jsonA = JSONObject.parseArray(email);
                List<String> emailList = new ArrayList<>();
                jsonA.stream().forEach(object -> {
                    JSONObject jsonO = (JSONObject) object;
                    emailList.add(jsonO.getString("email"));
                });

                return emailList;
            } catch (Exception e) {}
        }

        return new ArrayList<>();
    }

    /**
     * 拼接历史内容
     */
    private String formatHistoryEmail(String from, String sendTime, List<String> toList, List<String> ccList, String subject) {
        StringBuilder emailContent = new StringBuilder();

        emailContent.append("<div style=\"font-size: 12px;background:#efefef;padding:8px;\">")
                .append("<div><b>From: </b>&nbsp;<a href=\"mailto:").append(from).append("\" style=\"color: #1e7bf9; text-decoration: none;\" target=\"_blank\">").append(from).append("</a></div>")
                .append("<div><b>Send time: </b>&nbsp;").append(sendTime).append("</div>")
                .append("<div>").append(formatEmailRecipients("To", toList)).append("</div>");

        if (!ccList.isEmpty()) {
            emailContent.append("<div>").append(formatEmailRecipients("Cc", ccList)).append("</div>");
        }

        emailContent.append("<div><b>Subject: </b>&nbsp;").append(subject).append("</div>")
                .append("</div>");

        return emailContent.toString();
    }

    public static String formatEmailRecipients(String label, List<String> emails) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>").append(label).append(": </b>&nbsp;");

        for (int i = 0; i < emails.size(); i++) {
            sb.append("<a href=\"mailto:")
                    .append(emails.get(i))
                    .append("\" style=\"color: #1e7bf9; text-decoration: none;\" target=\"_blank\">")
                    .append(emails.get(i))
                    .append("</a>");
            if (i < emails.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    @Override
    public List<String> getUidsByTaskId(Long taskId) {
        return taskEmailMapper.getUidsByTaskId(taskId);
    }

    @Override
    public Map<Long, Integer> getEmailQuantityByIds(List<Long> ids, Integer type) {
        List<Map<String, Object>> emailQuantityByIds = taskEmailMapper.getEmailQuantityByIds(ids, type);
        if (emailQuantityByIds == null || emailQuantityByIds.size() == 0) {
            return new HashMap<>();
        }

        return emailQuantityByIds.stream().collect(Collectors.toMap(
                map -> Long.valueOf(map.get("taskId").toString()),
                map -> Integer.valueOf(map.get("quantity").toString()))
        );
    }

    @Override
    @Transactional
    public boolean delete(List<Long> ids) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        taskEmailMapper.removeTaskEmailIds(ids, userId, username, new Date());
        return true;
    }

    @Override
    public boolean sendEmail(TaskEmail taskEmail) {
        Long id = taskEmail.getId();
        // 查询邮件内容
        TaskEmailContent emailContent = taskEmailContentService.selectTaskEmailContentByEmailId(id);
        // 查询附件
        List<EmailAttachmentBO> emailAttachmentBOList = taskAttachmentService.listByEmailIds(Arrays.asList(id));

        // 获取邮箱信息
        Long taskId = taskEmail.getTaskId();
        Task task = taskService.selectTaskById(taskId);
        boolean isSuccess = false;
        String messageId = null;
        try {
            String[] mailTos = getEmails(taskEmail.getReceiver());
            String[] mailBccs = getEmails(taskEmail.getBcc());
            String[] mailCcs = getEmails(taskEmail.getCc());

            String title = taskEmail.getTitle();
            String content = emailContent != null ? emailContent.getContent() : null;

            String [] attachmentPaths = null;
            if (emailAttachmentBOList != null && !emailAttachmentBOList.isEmpty()) {
                List<String> filePathList =  new ArrayList<>();
                emailAttachmentBOList.stream().forEach(emailAttachmentBO -> {
                    filePathList.add(emailAttachmentBO.getPath());
                });
                attachmentPaths = filePathList.toArray(new String[filePathList.size()]);
            }

            // 是否追踪邮件
            Long traceEmailId = null;
            if (Optional.ofNullable(taskEmail.getTraceFlag()).orElse(false)) {
                traceEmailId = taskEmail.getId();
            }

            messageId = sendEmail(task.getOutgoingServer(), task.getOutgoingPort(), task.getOutgoingSslFlag(), task.getAccount(), task.getPassword(),
                    mailTos, mailCcs, mailBccs, title, content, taskEmail.getInReplyTo(), taskEmail.getReference(), traceEmailId,
                    task.getProxyServer(), task.getProxyPort(),
                    task.getProxyUsername(), task.getProxyPassword(), attachmentPaths);

            isSuccess = true;

            // 发送的邮件如果为自动回复或者转发的邮件，则不进行发件规则处理
            if (!taskEmail.getUid().contains(Constant.AUTO_RESPONSE_PREFIX) && !taskEmail.getUid().contains(Constant.FORWARD_TO_PREFIX)) {
                // 查询收发件规则
                List<TransceiverRuleVO> transceiverRuleList = transceiverRuleMapper.list(task.getCreateId());
                transceiverRuleList = transceiverRuleList.stream().filter(transceiverRule -> transceiverRule.getRuleType().intValue() == RuleTypeEnum.SEND.getType()).filter(transceiverRule -> transceiverRule.getStatus().intValue() == 1).collect(Collectors.toList());
                if (!transceiverRuleList.isEmpty()) {
                    // 发件规则处理
                    transceiverRuleHandler(taskEmail, content, transceiverRuleList);
                }
            }

        } catch (MessagingException e) {
            log.error("messagingException e:{}", e);
        } catch (Exception e) {
            log.error("发送失败，e:{}", e);
        }

        // 更新数据
        int status = isSuccess ? TaskExecutionStatusEnum.SUCCESS.getStatus() : TaskExecutionStatusEnum.FAILURE.getStatus();
        // 更新邮件发送状态
        updateStatusById(status, messageId, id);

        return true;
    }

    /**
     * 转发到
     * @param task
     * @param forwardTo
     * @param title
     * @param content
     */
    @Override
    public void forwardTo(Task task, String forwardTo, String title, String content) {
        if (StringUtils.isBlank(forwardTo)) return;

        Date nowDate = DateUtils.getNowDate();

        JSONArray jsonA = new JSONArray();
        JSONObject jsonO = new JSONObject();
        jsonO.put("name", forwardTo);
        jsonO.put("email", forwardTo);
        jsonA.add(jsonO);

        String receiver = JSONObject.toJSONString(jsonA);
        TaskEmail taskEmail = new TaskEmail();
        taskEmail.setUid(Constant.FORWARD_TO_PREFIX + IdUtils.fastSimpleUUID());
        taskEmail.setTaskId(task.getId());
        taskEmail.setFromer(task.getAccount());
        taskEmail.setReceiver(receiver);
        taskEmail.setTitle(title);
        taskEmail.setSendDate(new Date());
        taskEmail.setType(EmailTypeEnum.SEND.getType());
        taskEmail.setStatus(TaskExecutionStatusEnum.NOT_STARTED.getStatus());
        taskEmail.setCc("[]");
        taskEmail.setBcc("[]");
        taskEmail.setFolder("已发送");
        taskEmail.setPendingFlag(false);
        taskEmail.setPendingTime(nowDate);
        taskEmail.setCreateTime(nowDate);
        taskEmail.setUpdateTime(nowDate);
        taskEmailMapper.insertTaskEmail(taskEmail);

        TaskEmailContent taskEmailContent = new TaskEmailContent();
        taskEmailContent.setEmailId(taskEmail.getId());
        taskEmailContent.setContent(content);
        taskEmailContent.setCreateTime(nowDate);
        taskEmailContent.setUpdateTime(nowDate);
        taskEmailContentService.insertTaskEmailContent(taskEmailContent);
    }

    /**
     * 客户往来邮件列表
     * @param customerId
     * @return
     */
    @Override
    public List<DealingEmailListVO> dealingEmailList(Long customerId, Boolean attachmentFlag) {
        List<String> contactEmails = taskEmailMapper.getContactEmailList(customerId);
        if (contactEmails == null || contactEmails.isEmpty()) {
            return Collections.emptyList();
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 客户往来邮件列表
        List<DealingEmailListBO> dealingEmailBOList = taskEmailMapper.dealingEmailList(contactEmails, userId);

        List<Long> ids = dealingEmailBOList.stream().map(dealingEmailBO -> dealingEmailBO.getId()).collect(Collectors.toList());
        Map<Long, List<EmailAttachmentBO>> attachmentGroupMap = new HashMap<>();
        if (ids != null && !ids.isEmpty()) {
            // 查询邮件附件信息
            List<EmailAttachmentBO> emailAttachmentBOList = taskAttachmentService.listByEmailIds(ids);
            if (emailAttachmentBOList == null) emailAttachmentBOList = Collections.emptyList();
            attachmentGroupMap = emailAttachmentBOList.stream().collect(Collectors.groupingBy(emailAttachment -> emailAttachment.getEmailId()));
        }

        Map<Long, List<DealingEmailListBO>> emailIdListMap = dealingEmailBOList.stream()
                .collect(Collectors.groupingBy(DealingEmailListBO::getId, LinkedHashMap::new, Collectors.toList()));
        List<DealingEmailListVO> dealingEmailVOList = new ArrayList<>();

        for (Long emailId : emailIdListMap.keySet()) {
            List<DealingEmailListBO> list = emailIdListMap.get(emailId);
            DealingEmailListVO dealingEmailVO = new DealingEmailListVO();
            List<EmailAttachmentBO> emailAttachmentList = attachmentGroupMap.get(emailId);
            emailAttachmentList = emailAttachmentList == null ? Collections.emptyList() : emailAttachmentList;
            if (Optional.ofNullable(attachmentFlag).orElse(false) && emailAttachmentList.isEmpty()) {
                continue;
            }

            dealingEmailVO.setId(emailId);
            dealingEmailVO.setType(list.get(0).getType());
            dealingEmailVO.setSendDate(list.get(0).getSendDate());
            dealingEmailVO.setTitle(list.get(0).getTitle());
            dealingEmailVO.setContent(list.get(0).getContent());

            List<LabelListVO> labelVOList = new ArrayList<>();
            for (DealingEmailListBO dealingEmailListBO : list) {
                if (dealingEmailListBO.getId() == null) break;
                labelVOList.add(LabelListVO.builder().id(dealingEmailListBO.getLabelId()).name(dealingEmailListBO.getLabelName()).color(dealingEmailListBO.getLabelColor()).type(dealingEmailListBO.getLabelType()).build());
            }
            dealingEmailVO.setLabelList(labelVOList);
            dealingEmailVO.setEmailAttachmentList(emailAttachmentList);

            dealingEmailVOList.add(dealingEmailVO);
        }

        return dealingEmailVOList;
    }

    /**
     * 邮件详情
     * @param id
     * @return
     */
    @Override
    public EmailDetailVO detail(Long id) {
        EmailDetailVO emailDetailVO = taskEmailMapper.detail(id);

        List<Long> ids = Arrays.asList(emailDetailVO.getId());
        // 查询邮件附件信息
        List<EmailAttachmentBO> emailAttachmentBOList = taskAttachmentService.listByEmailIds(ids);
        if (emailAttachmentBOList == null) emailAttachmentBOList = Collections.emptyList();
        Map<Long, List<EmailAttachmentBO>> attachmentGroupMap = emailAttachmentBOList.stream().collect(Collectors.groupingBy(emailAttachment -> emailAttachment.getEmailId()));

        // 查询邮件标签信息
        List<EmailLabelBO> emailLabelBOList = labelService.listByEmailIds(ids);
        if (emailLabelBOList == null) emailLabelBOList = Collections.emptyList();
        Map<Long, List<EmailLabelBO>> labelGroupMap = emailLabelBOList.stream().collect(Collectors.groupingBy(emailLabel -> emailLabel.getEmailId()));


        if (attachmentGroupMap.containsKey(id)) {
            List<EmailAttachmentBO> emailAttachmentGroupList = attachmentGroupMap.get(id);
            emailDetailVO.setEmailAttachmentList(emailAttachmentGroupList);
        } else {
            emailDetailVO.setEmailAttachmentList(Collections.emptyList());
        }

        if (labelGroupMap.containsKey(id)) {
            List<EmailLabelBO> emailLabelGroupList = labelGroupMap.get(id);
            emailDetailVO.setEmailLabelList(emailLabelGroupList);
        } else {
            emailDetailVO.setEmailLabelList(Collections.emptyList());
        }

        return emailDetailVO;
    }

    /**
     * 更新任务发送邮件状态
     * @param taskId
     */
    @Override
    public void updateTaskSendEmailStatus(Long taskId) {
        taskEmailMapper.updateTaskSendEmailStatus(taskId);
    }

    /**
     * 查询未发送状态邮件
     * @param taskId
     * @return
     */
    @Override
    public List<TaskEmail> selectByUnSentStatus(Long taskId) {
        return taskEmailMapper.selectByUnSentStatus(taskId);
    }

    /**
     * 下属列表
     * @return
     */
    @Override //todo 待开发
    public List<UserInfoVO2> userList() {
        return null;
    }

    @Override
    public List<EmailListVO> correspondence(Long id) {
        TaskEmail taskEmail = taskEmailMapper.selectTaskEmailById(id);
        if (taskEmail == null) {
            throw new ServiceException();
        }

        // 这里获取的邮件链的列表可能不是最新的，所以需要去查出来最新的邮件链列表
        String reference = taskEmail.getReference();
        List<String> latestReferenceList = new ArrayList<>();
        if (StringUtils.isNotBlank(reference)) {
            String latestReference = getLatestReference(reference);
            String[] latestReferenceArray = latestReference.split(" ");
            latestReferenceList = Arrays.asList(latestReferenceArray);
        }

        if (latestReferenceList.isEmpty()) {
            return new ArrayList<>();
        }

        return taskEmailMapper.selectTaskEmailByMessageIdAndInReplyTo(latestReferenceList);
    }

    /**
     * 收发件规则条件处理
     * @param taskEmail
     * @param content
     * @param transceiverRuleList
     * @return
     */
    @Override
    public TransceiverRuleBO transceiverRuleConditionHandler(TaskEmail taskEmail, String content, List<TransceiverRuleVO> transceiverRuleList) {
        Boolean fixedFlag = false;
        Boolean readFlag = false;
        Long labelId = null;
        Long folderId = -1L;
        String forwardTo = null;
        Boolean pendingFlag = false;
        Date pendingTime = null;
        String autoResponse = null;
        String delFlag = "0";

        // 收邮件规则
        List<ExecuteConditionContentBO> executeConditionContentBOList = null;
        if (transceiverRuleList != null && !transceiverRuleList.isEmpty()) {
            Long taskId = taskEmail.getTaskId();
            Integer type = taskEmail.getType();
            for (TransceiverRuleVO transceiverRuleVO : transceiverRuleList) {
                Long executeTaskId = transceiverRuleVO.getExecuteTaskId();
                // 如果执行任务id不为0，且不等于当前任务id，则跳过
                if (executeTaskId.longValue() != 0L && executeTaskId.longValue() != taskId.longValue()) {
                    continue;
                }

                String executeConditionContent = transceiverRuleVO.getExecuteConditionContent();
                Gson gson = new Gson();
                try {
                    executeConditionContentBOList = Arrays.asList(gson.fromJson(executeConditionContent, ExecuteConditionContentBO[].class));
                } catch (Exception e) {
                    log.error("执行条件内容转换异常 ID：{}" +
                            "\n原因：{}", transceiverRuleVO.getId(), e);
                }

                boolean isRuleMet = true;
                for (ExecuteConditionContentBO executeConditionContentBO : executeConditionContentBOList) {
                    // 判断条件是否成立
                    EmailSimpleBO emailSimpleBO = EmailSimpleBO.builder().fromer(taskEmail.getFromer()).receiver(taskEmail.getReceiver()).cc(taskEmail.getCc()).subject(taskEmail.getTitle()).sendDate(taskEmail.getSendDate()).body(content).build();
                    boolean isConditionMet = emailColumnContext.handler(executeConditionContentBO, emailSimpleBO);

                    String andOr = executeConditionContentBO.getAndOr();
                    if (andOr.equals(AndOrEnum.AND)) {
                        if (!isConditionMet) {
                            isRuleMet = false;
                            break;
                        }
                    } else if (andOr.equals(AndOrEnum.OR)) {
                        if (isConditionMet) {
                            isRuleMet = true;
                            break;
                        } else {
                            isRuleMet = false;
                        }
                    }
                }

                if (!isRuleMet) continue;

                // 移动到【已删除邮件】
                if (transceiverRuleVO.getExecuteOperation() != null && transceiverRuleVO.getExecuteOperation().intValue() == 2) {
                    delFlag = "2";
                }

                // 是否固定
                if (Optional.ofNullable(transceiverRuleVO.getFixedFlag()).orElse(false)) {
                    fixedFlag = true;
                }

                // 是否已读
                if (type.intValue() == EmailTypeEnum.PULL.getType() && Optional.ofNullable(transceiverRuleVO.getReadFlag()).orElse(false)) {
                    readFlag = true;
                }

                // 标签
                if (Optional.ofNullable(transceiverRuleVO.getLabelFlag()).orElse(false)) {
                    labelId = transceiverRuleVO.getLabelId();
                }

                // 文件夹
                if (Optional.ofNullable(transceiverRuleVO.getFolderFlag()).orElse(false)) {
                    folderId = transceiverRuleVO.getFolderId();
                }

                // 转发到
                if (Optional.ofNullable(transceiverRuleVO.getForwardToFlag()).orElse(false)) {
                    // 转发到
                    forwardTo = transceiverRuleVO.getForwardTo();
                }

                // 标记为【待处理邮件】并设置稍后处理时间为
                if (Optional.of(transceiverRuleVO.getPendingFlag()).orElse(false) && transceiverRuleVO.getPendingType() != null) {
                    Integer pendingType = transceiverRuleVO.getPendingType();
                    pendingFlag = true;
                    pendingTime = taskEmail.getSendDate();
                    // 待处理类型 1.邮件接收时间 2.邮件接收时间之后的第
                    if (pendingType.intValue() == 2) {
                        if (transceiverRuleVO.getPendingDay() != null && transceiverRuleVO.getPendingTime() != null) {
                            Integer day = transceiverRuleVO.getPendingDay();
                            String time = transceiverRuleVO.getPendingTime();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            try {
                                Date parse = sdf.parse(time);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(pendingTime);
                                calendar.add(Calendar.DAY_OF_MONTH, day);
                                calendar.set(Calendar.HOUR_OF_DAY, parse.getHours());
                                calendar.set(Calendar.MINUTE, parse.getMinutes());
                                calendar.set(Calendar.SECOND, parse.getSeconds());
                                pendingTime = calendar.getTime();
                            } catch (Exception e) {
                                log.error("待处理时间转换异常：{}", e);
                            }
                        }
                    }
                }

                // 自动回复
                if (type.intValue() == EmailTypeEnum.PULL.getType() && Optional.ofNullable(transceiverRuleVO.getAutoResponseFlag()).orElse(false)) {
                    autoResponse = transceiverRuleVO.getAutoResponse();
                }

                // 判断其他发件规则: 1.继续执行 2.不再执行
                Integer otherSendingRules = transceiverRuleVO.getOtherSendingRules();
                if (otherSendingRules != null && otherSendingRules.intValue() == 2) {
                    break;
                }
            }
        }

        TransceiverRuleBO transceiverRuleBO = new TransceiverRuleBO();
        transceiverRuleBO.setFixedFlag(fixedFlag);
        transceiverRuleBO.setReadFlag(readFlag);
        transceiverRuleBO.setLabelId(labelId);
        transceiverRuleBO.setFolderId(folderId);
        transceiverRuleBO.setForwardTo(forwardTo);
        transceiverRuleBO.setPendingFlag(pendingFlag);
        transceiverRuleBO.setPendingTime(pendingTime);
        transceiverRuleBO.setAutoResponse(autoResponse);
        transceiverRuleBO.setDelFlag(delFlag);

        return transceiverRuleBO;
    }


    @Override
    public boolean read(List<Long> ids, Boolean readFlag) {
        return taskEmailMapper.batchUpdateReadFlag(ids, readFlag);
    }

    @Override
    public boolean spam(List<Long> ids, Boolean spamFlag) {
        return taskEmailMapper.batchUpdateSpamFlag(ids, spamFlag);
    }

    @Override
    public boolean moveEmailToFolder(List<Long> ids, Long folderId) {
        return taskEmailMapper.batchUpdateFolderId(ids, folderId);
    }

    @Override
    public String getEmailPath(Long id) {
        TaskEmail taskEmail = taskEmailMapper.selectTaskEmailById(id);
        if (taskEmail == null) {
            log.info("不存在id为 {} 的邮件", id);
            throw new ServiceException();
        }

        if (StringUtils.isBlank(taskEmail.getEmlPath())) {
            log.info("不存在id为 {} 的邮件文件路径", id);
            throw new ServiceException();
        }

        return taskEmail.getEmlPath();
    }

    @Override
    @Transactional
    public List<TaskAttachment> uploadAttachment(Long id) {
        TaskEmail taskEmail = taskEmailMapper.selectTaskEmailById(id);
        if (taskEmail == null) {
            log.info("不存在id为{}的邮件数据", id);
            throw new ServiceException();
        }

        if (StringUtils.isBlank(taskEmail.getEmlPath())) {
            throw new ServiceException("不存在邮件文件路径");
        }

        // 读取邮件文件，再写入到上传文件夹
        Path sourceFile = Paths.get(taskEmail.getEmlPath());
        if (sourceFile == null) {
            throw new ServiceException("读取不到文件");
        }

        return taskAttachmentService.uploadAttachment(sourceFile);
    }

    @Override
    public List<String> getAttachmentByEmailId(Long emailId) {
        return taskAttachmentService.getPathByEmailId(emailId);
    }

    /**
     * 标记为预处理
     * @param taskEmail
     * @return
     */
    @Override
    public boolean pending(TaskEmail taskEmail) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        TaskEmail dbTaskEmail = taskEmailMapper.selectTaskEmailById(taskEmail.getId());
        if (dbTaskEmail == null) {
            log.info("不存在id为{}的邮件记录", taskEmail.getId());
            throw new ServiceException();
        }

        dbTaskEmail.setPendingFlag(taskEmail.getPendingFlag());
        dbTaskEmail.setPendingTime(taskEmail.getPendingTime());
        dbTaskEmail.setUpdateId(userId);
        dbTaskEmail.setUpdateBy(username);
        taskEmailMapper.updateTaskEmail(dbTaskEmail);
        return true;
    }

    @Override
    public boolean moveEmailToLabel(Long id, Long labelId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 删除该记录
        taskEmailLabelService.deleteByEmailIdAndLabelId(id, labelId, userId);

        Date now = new Date();
        TaskEmailLabel taskEmailLabel = new TaskEmailLabel();
        taskEmailLabel.setEmailId(id);
        taskEmailLabel.setLabelId(labelId);
        taskEmailLabel.setCreateId(userId);
        taskEmailLabel.setCreateBy(username);
        taskEmailLabel.setCreateTime(now);
        taskEmailLabel.setUpdateId(userId);
        taskEmailLabel.setUpdateBy(username);
        taskEmailLabel.setUpdateTime(now);
        taskEmailLabelService.insertTaskEmailLabel(taskEmailLabel);
        return true;
    }

    @Override
    public boolean deleteLabel(Long emailId, Long labelId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        taskEmailLabelService.deleteByEmailIdAndLabelId(emailId, labelId, userId);
        return true;
    }

    @Override
    public MenuCountVO countMenu() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {

            // 收件箱
            Future<List<MenuInboxTaskCountVO>> allReceivedCountFuture = executorService.submit(() -> countMenuInboxTaskCount(userId));
            // 待办
            Future<Integer> pendingMailCountFuture = executorService.submit(() -> pendingMailCount(userId));
            // 草稿箱
            Future<Integer> draftsCountFuture = executorService.submit(() -> draftsCount(userId));

            List<MenuInboxTaskCountVO> menuInboxTaskCountList = allReceivedCountFuture.get();
            int allReceivedCount = 0;
            for (MenuInboxTaskCountVO menuInboxTaskCountVO : menuInboxTaskCountList) {
                allReceivedCount += menuInboxTaskCountVO.getCount();
            }

            MenuCountVO menuCountVO = new MenuCountVO();
            menuCountVO.setAllReceivedCount(allReceivedCount);
            menuCountVO.setMenuInboxTaskCountList(menuInboxTaskCountList);
            menuCountVO.setAnUnreadMailCount(allReceivedCount);
            menuCountVO.setPendingMailCount(pendingMailCountFuture.get());
            menuCountVO.setDraftsCount(draftsCountFuture.get());

            return menuCountVO;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 客户列表
     * @param customerId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Pair<Integer, List<Map<String, List<EmailListVO>>>> customerEmailList(Long customerId, Boolean fixedFlag, Boolean attachmentFlag, List<String> emailList, Integer type, List<Long> labelIdList, Integer keywordType, String keyword, Integer pageNum, Integer pageSize) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        int count = taskEmailMapper.customerEmailCount(userId, customerId, fixedFlag, attachmentFlag, emailList, type, labelIdList, keywordType, keyword);
        if (count <= 0) {
            return Pair.of(0, new ArrayList<>());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<EmailListVO> emailListVOList = taskEmailMapper.customerEmailList(userId, customerId, fixedFlag, attachmentFlag, emailList, type, labelIdList, keywordType, keyword, offset, limit);
        if (emailListVOList == null || emailListVOList.isEmpty()) {
            return Pair.of(count, new ArrayList<>());
        }

        List<Long> ids = emailListVOList.stream().map(emailListVO -> emailListVO.getId()).collect(Collectors.toList());
        // 查询邮件附件信息
        List<EmailAttachmentBO> emailAttachmentBOList = taskAttachmentService.listByEmailIds(ids);
        if (emailAttachmentBOList == null) emailAttachmentBOList = Collections.emptyList();
        Map<Long, List<EmailAttachmentBO>> attachmentGroupMap = emailAttachmentBOList.stream().collect(Collectors.groupingBy(emailAttachment -> emailAttachment.getEmailId()));

        // 查询邮件标签信息
        List<EmailLabelBO> emailLabelBOList = labelService.listByEmailIds(ids);
        if (emailLabelBOList == null) emailLabelBOList = Collections.emptyList();
        Map<Long, List<EmailLabelBO>> labelGroupMap = emailLabelBOList.stream().collect(Collectors.groupingBy(emailLabel -> emailLabel.getEmailId()));

        emailListVOList.stream().forEach(emailListVO -> {
            Long id = emailListVO.getId();
            if (attachmentGroupMap.containsKey(id)) {
                List<EmailAttachmentBO> emailAttachmentGroupList = attachmentGroupMap.get(id);
                emailListVO.setEmailAttachmentList(emailAttachmentGroupList);
            } else {
                emailListVO.setEmailAttachmentList(Collections.emptyList());
            }

            if (labelGroupMap.containsKey(id)) {
                List<EmailLabelBO> emailLabelGroupList = labelGroupMap.get(id);
                emailListVO.setEmailLabelList(emailLabelGroupList);
            } else {
                emailListVO.setEmailLabelList(Collections.emptyList());
            }
        });

        Map<String, List<EmailListVO>> data = new LinkedHashMap<>();
        emailListVOList.stream().forEach(emailListVO -> {
            Date sendDate = emailListVO.getSendDate();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());
            String dynamicLabel = getDynamicLabel(localDateTime);

            if (data.containsKey(dynamicLabel)) {
                data.get(dynamicLabel).add(emailListVO);
            } else {
                data.put(dynamicLabel, new ArrayList<EmailListVO>() {{
                    add(emailListVO);
                }});
            }
        });

        List<Map<String, List<EmailListVO>>> dataList = new ArrayList<>();
        data.forEach((key, value) -> {
            dataList.add(new HashMap<String, List<EmailListVO>>() {{
                put(key, value);
            }});
        });

        return Pair.of(count, dataList);
    }

    private Integer draftsCount(Long userId) {
        return taskEmailMapper.countDraftsNum(userId);
    }

    /**
     * 统计预处理邮件数量
     * @param userId
     */
    private Integer pendingMailCount(Long userId) {
        return taskEmailMapper.countPendingMailNum(userId);
    }

    /**
     * 统计收件箱未读邮件数量
     * @param userId
     */
    private List<MenuInboxTaskCountVO> countMenuInboxTaskCount(Long userId) {
        return taskEmailMapper.countUnReadEmailNum(userId);
    }

    /**
     * 获取最新Reference
     * @param reference
     * @return
     */
    private String getLatestReference(String reference) {
        return taskEmailMapper.getLatestReference(reference);
    }

    /**
     * 更新邮件发送状态
     * @param status
     * @param messageId
     * @param id
     */
    private void updateStatusById(int status, String messageId, Long id) {
        taskEmailMapper.updateStatusById(status, messageId, id);
    }

    /**
     * 获取收件人、密送、抄送邮箱
     * @param mail
     */
    private String[] getEmails(String mail) throws Exception {
        if (StringUtils.isNotEmpty(mail)) {
            JSONArray mailJsonA = JSONObject.parseArray(mail);
            List<String> emailList = new ArrayList<>();
            mailJsonA.stream().forEach(receiverJson -> {
                JSONObject json = (JSONObject) receiverJson;
                emailList.add(json.getString("email"));
            });

            return emailList.toArray(new String[emailList.size()]);
        }

        return null;
    }

    /**
     * 发送邮件
     * @param host
     * @param port
     * @param useSSL
     * @param mailFrom
     * @param password
     * @param mailTos
     * @param mailCcs
     * @param mailBccs
     * @param title
     * @param content
     * @param proxyHost
     * @param proxyPort
     * @param attachmentPaths
     * @return 返回邮件的唯一ID
     * @throws MessagingException
     */
    public String sendEmail(String host, Integer port, boolean useSSL, String mailFrom, String password,
                          String[] mailTos, String[] mailCcs, String[] mailBccs,
                          String title, String content, String inReplyTo, String references, Long traceEmailId, String proxyHost, Integer proxyPort,
                          String proxyUser, String proxyPassword, String[] attachmentPaths) throws Exception {
        Properties properties = new Properties();

        if (useSSL) {
            properties.put("mail.smtps.host", host);
            properties.put("mail.smtps.port", port);
            properties.put("mail.smtps.auth", "true");
            properties.put("mail.smtps.starttls.enable", "true");
            properties.put("mail.smtps.ssl.checkserveridentity", "true");
            properties.put("mail.smtps.ssl.trust", host);
            properties.put("mail.smtps.ssl.protocols", "TLSv1.2");
        } else {
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
        }

        if (proxyHost != null && !proxyHost.isEmpty()) {
            if (useSSL) {
                properties.put("mail.smtps.proxy.host", proxyHost);
                properties.put("mail.smtps.proxy.port", proxyPort);
                properties.put("mail.smtps.proxy.user", proxyUser);
                properties.put("mail.smtps.proxy.password", proxyPassword);
            } else {
                properties.put("mail.smtp.proxy.host", proxyHost);
                properties.put("mail.smtp.proxy.port", proxyPort);
                properties.put("mail.smtp.proxy.user", proxyUser);
                properties.put("mail.smtp.proxy.password", proxyPassword);
            }
        }

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);
        if (StringUtils.isNotBlank(inReplyTo)) {
            msg.setHeader("In-Reply-To", inReplyTo);
        }
        if (StringUtils.isNotBlank(references)) {
            msg.setHeader("References", references);
        }

        msg.setFrom(new InternetAddress(mailFrom));
        // 设置收件人
        if (mailTos != null && mailTos.length > 0) {
            InternetAddress[] toAddresses = InternetAddress.parse(String.join(",", mailTos));
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
        }

        // 设置抄送人
        if (mailCcs != null && mailCcs.length > 0) {
            InternetAddress[] ccAddresses = InternetAddress.parse(String.join(",", mailCcs));
            msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        }

        // 设置密送人
        if (mailBccs != null && mailBccs.length > 0) {
            InternetAddress[] bccAddresses = InternetAddress.parse(String.join(",", mailBccs));
            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
        }

        msg.setSubject(title);

        // 创建多部分消息
        Multipart multipart = new MimeMultipart();

        // 创建文本消息部分
        BodyPart messageBodyPart = new MimeBodyPart();
        if (traceEmailId == null) {
            messageBodyPart.setContent(content, "text/html;charset=UTF-8");
        } else {
            messageBodyPart.setContent(content + String.format(HTML_IMG_TRACE, traceServer, traceEmailId), "text/html;charset=UTF-8");
        }

        multipart.addBodyPart(messageBodyPart);

        // 添加附件
        if (attachmentPaths != null && attachmentPaths.length > 0) {
            for (String attachmentPath : attachmentPaths) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(attachmentPath);
                attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                String encodedFileName = null;
                try {
                    encodedFileName = MimeUtility.encodeText(fileDataSource.getName(), "UTF-8", null);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                attachmentPart.setFileName(encodedFileName);
                multipart.addBodyPart(attachmentPart);
            }
        }

        // 设置消息的多部分内容
        msg.setContent(multipart);

        if (useSSL) {
            Transport transport = session.getTransport("smtps");
            transport.connect(host, mailFrom, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } else {
            Transport.send(msg);
        }

        String[] header = msg.getHeader("Message-ID");
        return header != null && header.length > 0 ? header[0] : null;
    }

    /**
     * 收发件规则处理
     * @param taskEmail
     * @param content
     * @param transceiverRuleList
     */
    @Override
    public void transceiverRuleHandler(TaskEmail taskEmail, String content, List<TransceiverRuleVO> transceiverRuleList) {
        // 判断是否存在发件规则
        if (transceiverRuleList == null || transceiverRuleList.isEmpty()) return;

        Long emailId = taskEmail.getId();
        Long taskId = taskEmail.getTaskId();
        Date nowDate = DateUtils.getNowDate();
        // 收件规则条件处理
        TransceiverRuleBO transceiverRuleBO = transceiverRuleConditionHandler(taskEmail, content, transceiverRuleList);

        taskEmail.setFixedFlag(transceiverRuleBO.getFixedFlag());
        taskEmail.setReadFlag(transceiverRuleBO.getReadFlag());
        taskEmail.setFolderId(transceiverRuleBO.getFolderId());
        taskEmail.setPendingFlag(transceiverRuleBO.getPendingFlag());
        taskEmail.setPendingTime(transceiverRuleBO.getPendingTime());
        taskEmail.setDelFlag(transceiverRuleBO.getDelFlag());
        updateTaskEmail(taskEmail);

        // 保存邮件标签
        if (transceiverRuleBO.getLabelId() != null) {
            TaskEmailLabel taskEmailLabel = new TaskEmailLabel();
            taskEmailLabel.setEmailId(emailId);
            taskEmailLabel.setLabelId(transceiverRuleBO.getLabelId());
            taskEmailLabel.setCreateTime(nowDate);
            taskEmailLabelMapper.insertTaskEmailLabel(taskEmailLabel);
        }

        Task task = taskMapper.selectTaskById(taskId);
        if (task == null) return;

        // 转发
        forwardTo(task, transceiverRuleBO.getForwardTo(), taskEmail.getTitle(), content);

        if (StringUtils.isBlank(transceiverRuleBO.getAutoResponse())) return;

        // 自动回复
        autoResponse(task, taskEmail.getFromer(), taskEmail.getTitle(), transceiverRuleBO.getAutoResponse(), taskEmail.getMessageId());
    }

    /**
     * 查询是否在4天内已经回复过邮件
     * @param fromer
     * @param receiver
     */
    @Override
    public boolean checkRepliedWithinFourDays(String fromer, String receiver, Long createId) {
        int count = taskEmailMapper.countRepliedWithinFourDays(fromer, receiver, createId, Constant.AUTO_RESPONSE_PREFIX);
        return count > 0 ? true : false;
    }

    private String getDynamicLabel(LocalDateTime mailDateTime) {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        mailDateTime = mailDateTime.truncatedTo(ChronoUnit.DAYS); // 修正的部分

        long daysBetween = ChronoUnit.DAYS.between(mailDateTime, now);
        if (daysBetween == 0) {
            return "今天";
        } else if (daysBetween == 1) {
            return "昨天";
        } else if (daysBetween > 1 && daysBetween <= 7) {
            DayOfWeek dayOfWeek = mailDateTime.getDayOfWeek();
            return "上周" + dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.CHINESE);
        } else if (mailDateTime.getYear() == now.getYear()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日");
            return mailDateTime.format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
            return mailDateTime.format(formatter);
        }
    }
}
