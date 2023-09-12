package com.ruoyi.email.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.TaskExecutionStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.email.domain.TaskEmailContent;
import com.ruoyi.email.domain.dto.email.EmailQuickReplyDTO;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.email.EmailListVO;
import com.ruoyi.email.service.ITaskEmailAttachmentService;
import com.ruoyi.email.service.ITaskEmailContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailMapper;
import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.service.ITaskEmailService;

import javax.annotation.Resource;

/**
 * 邮件Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-09-12
 */
@Slf4j
@Service
public class TaskEmailServiceImpl implements ITaskEmailService 
{
    @Resource
    private TaskEmailMapper taskEmailMapper;
    @Resource
    private ITaskEmailContentService taskEmailContentService;
    @Resource
    private ITaskEmailAttachmentService taskEmailAttachmentService;

    /**
     * 查询邮件
     * 
     * @param id 邮件主键
     * @return 邮件
     */
    @Override
    public TaskEmail selectTaskEmailById(Long id)
    {
        return taskEmailMapper.selectTaskEmailById(id);
    }

    /**
     * 查询邮件列表
     * 
     * @param taskEmail 邮件
     * @return 邮件
     */
    @Override
    public List<TaskEmail> selectTaskEmailList(TaskEmail taskEmail)
    {
        return taskEmailMapper.selectTaskEmailList(taskEmail);
    }

    /**
     * 新增邮件
     * 
     * @param taskEmail 邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmail(TaskEmail taskEmail)
    {
        taskEmail.setCreateTime(DateUtils.getNowDate());
        try {
            return taskEmailMapper.insertTaskEmail(taskEmail);
        } catch (Exception e) {
            log.error("{}", e);
            log.error(taskEmail.getReferences());
        }
        return 0;
    }

    /**
     * 修改邮件
     * 
     * @param taskEmail 邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmail(TaskEmail taskEmail)
    {
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
    public int deleteTaskEmailByIds(Long[] ids)
    {
        return taskEmailMapper.deleteTaskEmailByIds(ids);
    }

    /**
     * 删除邮件信息
     * 
     * @param id 邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailById(Long id)
    {
        return taskEmailMapper.deleteTaskEmailById(id);
    }

    /**
     * 查询邮件列表（首页）
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
    public Pair<Integer, List<Map<String, List<EmailListVO>>>> list(List<Long> taskIdList, Integer type, Boolean readFlag, Boolean pendingFlag, String delFlag, Boolean draftsFlag, Integer pageNum, Integer pageSize) {
        if (taskIdList.isEmpty()) {
            return Pair.of(0, new ArrayList<>());
        }

        Integer status = Optional.ofNullable(draftsFlag).orElse(false) ? TaskExecutionStatusEnum.NOT_STARTED.getStatus() : null;
        int count = taskEmailMapper.count(taskIdList, type, readFlag, pendingFlag, delFlag, status);
        if (count <= 0) {
            return Pair.of(0, new ArrayList<>());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<EmailListVO> emailListVOList = taskEmailMapper.selectTaskEmailPage(taskIdList, type, readFlag, pendingFlag, delFlag, status, offset, limit);

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
     * @param dto
     * @return
     */
    @Override
    public Long save(EmailSendSaveDTO dto) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = new Date();


        TaskEmail taskEmail = new TaskEmail();
        BeanUtils.copyProperties(dto, taskEmail);
        taskEmail.setUid(IdUtils.fastSimpleUUID());
        taskEmail.setType(EmailTypeEnum.SEND.getType());
        taskEmail.setStatus(TaskExecutionStatusEnum.NOT_STARTED.getStatus());
        taskEmail.setCreateId(userId);
        taskEmail.setCreateBy(username);
        taskEmail.setCreateTime(now);
        taskEmail.setUpdateId(userId);
        taskEmail.setUpdateBy(username);
        taskEmail.setUpdateTime(now);
        taskEmailMapper.insertTaskEmail(taskEmail);

        Long emailSendId = taskEmail.getId();
        // 保存邮件内容
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailSendId);
        emailContent.setContent(dto.getContent());
        emailContent.setType(EmailTypeEnum.SEND.getType());
        emailContent.setCreateId(userId);
        emailContent.setCreateBy(username);
        emailContent.setCreateTime(now);
        emailContent.setUpdateId(userId);
        emailContent.setUpdateBy(username);
        emailContent.setUpdateTime(now);
        taskEmailContentService.insertTaskEmailContent(emailContent);

        // 更新邮件附件的emailId
        List<Long> attachmentIdList = dto.getAttachmentIdList();
        if (attachmentIdList != null && !attachmentIdList.isEmpty()) {
            taskEmailAttachmentService.updateEmailIdByIds(emailSendId, attachmentIdList);
        }
        return emailSendId;
    }

    /**
     * 邮件发送-（写信）
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        return taskEmailMapper.updateFixed(id, fixedFlag, userId);
    }

    @Override
    public boolean quickReply(EmailQuickReplyDTO emailQuickReplyDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        Long id = emailQuickReplyDTO.getId();
        TaskEmail pullTaskEmail = taskEmailMapper.getTaskEmailById(id, userId);
        if (pullTaskEmail == null) throw new ServiceException();

        String username = loginUser.getUsername();
        Date now = new Date();

        TaskEmail taskEmail = new TaskEmail();
        taskEmail.setUid(IdUtils.fastSimpleUUID());
        taskEmail.setTaskId(pullTaskEmail.getId());
        taskEmail.setFromer(pullTaskEmail.getFromer());
        taskEmail.setReceiver(pullTaskEmail.getReceiver());
        taskEmail.setBcc(pullTaskEmail.getBcc());
        taskEmail.setCc(pullTaskEmail.getCc());
        taskEmail.setTitle(pullTaskEmail.getTitle());
        taskEmail.setType(EmailTypeEnum.SEND.getType());
        taskEmail.setStatus(TaskExecutionStatusEnum.IN_PROGRESS.getStatus());
        taskEmail.setCreateId(userId);
        taskEmail.setCreateBy(username);
        taskEmail.setCreateTime(now);
        taskEmail.setUpdateId(userId);
        taskEmail.setUpdateBy(username);
        taskEmail.setUpdateTime(now);
        taskEmail.setInReplyTo(pullTaskEmail.getMessageId());

        taskEmailMapper.insertTaskEmail(taskEmail);
        return true;
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

    private String getDynamicLabel(LocalDateTime mailDateTime) {
        LocalDateTime now = LocalDateTime.now();

        long daysBetween = ChronoUnit.DAYS.between(mailDateTime, now);
        if (daysBetween == 0) {
            return "今天";
        } else if (daysBetween == 1) {
            return "昨天";
        } else if (daysBetween > 1 && daysBetween <= 7) {
            DayOfWeek dayOfWeek = mailDateTime.getDayOfWeek();
            return "上周" + dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.CHINESE);
        } else if (mailDateTime.getYear() == now.getYear()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            return mailDateTime.format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            return mailDateTime.format(formatter);
        }
    }
}
