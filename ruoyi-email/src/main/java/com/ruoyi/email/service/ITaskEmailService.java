package com.ruoyi.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.TaskAttachment;
import com.ruoyi.email.domain.TransceiverRule;
import com.ruoyi.email.domain.bo.TransceiverRuleBO;
import com.ruoyi.email.domain.dto.email.EmailQuickReplyDTO;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.*;
import org.springframework.data.util.Pair;


/**
 * 邮件Service接口
 * 
 * @author tangJM.
 * @date 2023-09-12
 */
public interface ITaskEmailService 
{
    /**
     * 查询邮件
     * 
     * @param id 邮件主键
     * @return 邮件
     */
    public TaskEmail selectTaskEmailById(Long id);

    /**
     * 查询邮件列表
     * 
     * @param taskEmail 邮件
     * @return 邮件集合
     */
    public List<TaskEmail> selectTaskEmailList(TaskEmail taskEmail);

    /**
     * 新增邮件
     * 
     * @param taskEmail 邮件
     * @return 结果
     */
    public int insertTaskEmail(TaskEmail taskEmail);

    /**
     * 修改邮件
     * 
     * @param taskEmail 邮件
     * @return 结果
     */
    public int updateTaskEmail(TaskEmail taskEmail);

    /**
     * 批量删除邮件
     * 
     * @param ids 需要删除的邮件主键集合
     * @return 结果
     */
    public int deleteTaskEmailByIds(Long[] ids);

    /**
     * 删除邮件信息
     * 
     * @param id 邮件主键
     * @return 结果
     */
    public int deleteTaskEmailById(Long id);

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
    Pair<Integer, List<Map<String, List<EmailListVO>>>> list(List<Long> taskIdList, Integer type, Boolean readFlag, Boolean pendingFlag, Boolean spamFlag, String delFlag, Boolean draftsFlag, Boolean traceFlag, Boolean fixedFlag, Boolean attachmentFlag, Boolean customerFlag, Long folderId, Long labelId, Integer pageNum, Integer pageSize);

    /**
     * 保存发送邮件
     * @param dto
     * @return
     */
    Long save(EmailSendSaveDTO dto);

    /**
     * 邮件发送（写信）
     * @param id
     * @return
     */
    boolean send(Long id);

    /**
     * 邮件固定
     * @param id
     * @param fixedFlag
     * @return
     */
    boolean fixed(Long id, Boolean fixedFlag);

    /**
     * 快速回复
     * @param emailQuickReplyDTO
     * @return
     */
    boolean quickReply(EmailQuickReplyDTO emailQuickReplyDTO);

    /**
     * 查询拉取邮件的uid
     * @param taskId
     * @return
     */
    List<String> getUidsByTaskId(Long taskId);

    /**
     * 获取拉取邮件数量
     * @param ids
     * @param type
     * @return
     */
    Map<Long, Integer> getEmailQuantityByIds(List<Long> ids, Integer type);

    /**
     * 邮件删除
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);

    /**
     * 发送邮件
     * @param taskEmail
     * @return
     */
    boolean sendEmail(TaskEmail taskEmail);

    /**
     * 往来邮件
     * @param id
     * @return
     */
    List<EmailListVO> correspondence(Long id);

    /**
     * 邮件是否已读
     * @param ids
     * @param readFlag
     * @return
     */
    boolean read(List<Long> ids, Boolean readFlag);

    /**
     * 更新邮件是否为垃圾邮件
     * @param ids
     * @param spamFlag
     * @return
     */
    boolean spam(List<Long> ids, Boolean spamFlag);

    /**
     * 移动邮件到文件夹
     * @param ids
     * @param folderId
     * @return
     */
    boolean moveEmailToFolder(List<Long> ids, Long folderId);

    /**
     * 根据id获取邮件文件
     * @param id
     * @return
     */
    String getEmailPath(Long id);

    /**
     * 上传附件
     * @param id
     * @return
     */
    List<TaskAttachment> uploadAttachment(Long id);

    /**
     * 查询邮件的附件路径
     * @param emailId
     * @return
     */
    List<String> getAttachmentByEmailId(Long emailId);

    /**
     * 标记待处理
     * @param taskEmail
     * @return
     */
    boolean pending(TaskEmail taskEmail);

    /**
     * 移动邮件到标签
     * @param id
     * @param labelId
     * @return
     */
    boolean moveEmailToLabel(Long id, Long labelId);

    /**
     * 删除邮件标签
     * @param emailId
     * @param labelId
     * @return
     */
    boolean deleteLabel(Long emailId, Long labelId);

    /**
     * 统计菜单邮件数量
     * @return
     */
    MenuCountVO countMenu();

    /**
     * 客户邮件列表
     * @param customerId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pair<Integer, List<Map<String, List<EmailListVO>>>> customerEmailList(Long customerId, Boolean fixedFlag, Boolean attachmentFlag, List<String> emailList, Integer type, List<Long> labelIdList, Integer keywordType, String keyword, Integer pageNum, Integer pageSize);

    /**
     * 自动回复
     * @param task
     * @param fromer
     * @param title
     * @param reContent
     * @param messageId
     */
    void autoResponse(Task task, String fromer, String title, String reContent, String messageId);

    /**
     * 收发件规则处理
     */
    void transceiverRuleHandler(TaskEmail taskEmail, String content, List<TransceiverRuleVO> transceiverRuleList);

    /**
     * 收发件规则条件处理
     */
    TransceiverRuleBO transceiverRuleConditionHandler(TaskEmail taskEmail, String content, List<TransceiverRuleVO> transceiverRuleList);

    /**
     * 转发到
     */
    void forwardTo(Task task, String forwardTo, String title, String content);

    /**
     * 查询是否在4天内已经回复过邮件
     * @param fromer
     * @param receiver
     * @param createId
     * @return
     */
    boolean checkRepliedWithinFourDays(String fromer, String receiver, Long createId);

    /**
     * 客户往来邮件列表
     * @param customerId
     * @param attachmentFlag
     * @return
     */
    List<DealingEmailListVO> dealingEmailList(Long customerId, Boolean attachmentFlag);

    /**
     * 邮件详情
     * @param id
     * @return
     */
    EmailDetailVO detail(Long id);

    /**
     * 更新任务邮件发送状态
     * @param taskId
     */
    void updateTaskSendEmailStatus(Long taskId);

    /**
     * 查询未发送状态邮件
     * @param taskId
     * @return
     */
    List<TaskEmail> selectByUnSentStatus(Long taskId);

    /**
     * 下属列表
     * @return
     */
    List<UserInfoVO2> userList();

    /**
     * 针对收件箱的历史邮件
     */
    void applyToHistoryMailForInbox(TransceiverRule transceiverRule);

    /**
     * 针对收件箱及所有文件夹的历史邮件（不包括已删除）
     * @param transceiverRule
     */
    void applyToHistoryMailForInboxAndAllFolder(TransceiverRule transceiverRule);
}
