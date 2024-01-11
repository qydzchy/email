package com.ruoyi.email.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.bo.DealingEmailListBO;
import com.ruoyi.email.domain.vo.DealingEmailListVO;
import com.ruoyi.email.domain.vo.EmailDetailVO;
import com.ruoyi.email.domain.vo.EmailListVO;
import com.ruoyi.email.domain.vo.MenuInboxTaskCountVO;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件Mapper接口
 * 
 * @author tangJM.
 * @date 2023-09-12
 */
public interface TaskEmailMapper 
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
     * 删除邮件
     * 
     * @param id 邮件主键
     * @return 结果
     */
    public int deleteTaskEmailById(Long id);

    /**
     * 批量删除邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailByIds(Long[] ids);

    /**
     * 统计总数
     * @param taskIdList
     * @param type
     * @param readFlag
     * @param pendingFlag
     * @param delFlag
     * @param statusList
     * @return
     */
    int count(@Param("taskIdList") List<Long> taskIdList, @Param("type") Integer type, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("spamFlag") Boolean spamFlag, @Param("delFlag") String delFlag, @Param("traceFlag") Boolean traceFlag, @Param("fixedFlag") Boolean fixedFlag, @Param("attachmentFlag") Boolean attachmentFlag, @Param("folderId") Long folderId, @Param("labelId") Long labelId, @Param("statusList") List<Integer> statusList);

    /**
     * 查询邮件列表数据（首页）
     * @param taskIdList
     * @param type
     * @param readFlag
     * @param pendingFlag
     * @param delFlag
     * @param statusList
     * @param offset
     * @param limit
     * @return
     */
    List<EmailListVO> selectTaskEmailPage(@Param("taskIdList") List<Long> taskIdList, @Param("type") Integer type, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("spamFlag") Boolean spamFlag, @Param("delFlag") String delFlag, @Param("traceFlag") Boolean traceFlag, @Param("fixedFlag") Boolean fixedFlag, @Param("attachmentFlag") Boolean attachmentFlag, @Param("folderId") Long folderId, @Param("labelId") Long labelId, @Param("statusList") List<Integer> statusList, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据id获取邮件信息
     * @param id
     * @param createId
     * @return
     */
    TaskEmail getTaskEmailById(@Param("id") Long id, @Param("createId") Long createId);

    /**
     * 更新固定状态
     * @param id
     * @param fixedFlag
     * @return
     */
    boolean updateFixedFlag(@Param("id") Long id, @Param("fixedFlag") Boolean fixedFlag);

    /**
     * 查询拉取邮件的uid
     * @param taskId
     * @return
     */
    List<String> getUidsByTaskId(@Param("taskId") Long taskId);

    /**
     * 获取拉取邮件数量
     * @param ids
     * @param type
     * @return
     */
    List<Map<String, Object>> getEmailQuantityByIds(@Param("ids") List<Long> ids, @Param("type") Integer type);

    /**
     * 批量删除邮件（物理删除）
     * @param ids
     * @param updateId
     * @param updateBy
     * @param updateTime
     * @return
     */
    void removeTaskEmailIds(@Param("ids") List<Long> ids, @Param("updateId") Long updateId, @Param("updateBy") String updateBy, @Param("updateTime") Date updateTime);

    /**
     * 根据邮件状态
     * @param status
     * @param messageId
     * @param id
     */
    void updateStatusById(@Param("status") int status, @Param("messageId") String messageId, @Param("id") Long id);

    /**
     * 获取最新的reference
     * @param reference
     * @return
     */
    String getLatestReference(@Param("reference") String reference);

    /**
     * 往来邮件
     * @param latestReferenceList
     * @return
     */
    List<EmailListVO> selectTaskEmailByMessageIdAndInReplyTo(@Param("latestReferenceList") List<String> latestReferenceList);

    /**
     * 批量更新邮件是否已读
     * @param ids
     * @param readFlag
     * @return
     */
    boolean batchUpdateReadFlag(@Param("ids") List<Long> ids, @Param("readFlag") Boolean readFlag);

    /**
     * 批量更新邮件是否为垃圾邮件
     * @param ids
     * @param spamFlag
     * @return
     */
    boolean batchUpdateSpamFlag(@Param("ids") List<Long> ids, @Param("spamFlag") Boolean spamFlag);

    /**
     * 批量更新邮件文件夹ID
     * @param ids
     * @param folderId
     * @return
     */
    boolean batchUpdateFolderId(@Param("ids") List<Long> ids, @Param("folderId") Long folderId);

    /**
     * 统计未读邮件数量
     * @param userId
     * @return
     */
    List<MenuInboxTaskCountVO> countUnReadEmailNum(@Param("createId") Long userId);

    /**
     * 统计待办邮件数量
     * @param userId
     * @return
     */
    Integer countPendingMailNum(@Param("createId") Long userId);

    /**
     * 统计草稿数量
     * @param userId
     * @return
     */
    Integer countDraftsNum(@Param("createId") Long userId);

    /**
     * 统计客户邮件数
     * @param customerId
     * @return
     */
    int customerEmailCount(@Param("customerId") Long customerId, @Param("fixedFlag") Boolean fixedFlag, @Param("attachmentFlag") Boolean attachmentFlag, @Param("emailList") List<String> emailList, @Param("type") Integer type, @Param("labelIdList") List<Long> labelIdList, @Param("keywordType") Integer keywordType, @Param("keyword") String keyword);

    /**
     * 客户邮件列表
     * @param customerId
     * @param offset
     * @param limit
     * @return
     */
    List<EmailListVO> customerEmailList(@Param("customerId") Long customerId, @Param("fixedFlag") Boolean fixedFlag, @Param("attachmentFlag") Boolean attachmentFlag, @Param("emailList") List<String> emailList, @Param("type") Integer type, @Param("labelIdList") List<Long> labelIdList, @Param("keywordType") Integer keywordType, @Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 判断邮件是否存在
     * @param taskId
     * @param folderId
     * @param sentDate
     * @return
     */
    int countByEmlFileParam(@Param("taskId") Long taskId, @Param("folderId") Long folderId, @Param("title") String title, @Param("sendDate") Date sentDate);

    /**
     * 获取客户联系人邮箱
     * @param customerId
     * @return
     */
    List<String> getContactEmailList(@Param("customerId") Long customerId);

    /**
     * 统计在4天内已经回复过邮件数量
     * @param fromer
     * @param receiver
     * @return
     */
    int countRepliedWithinFourDays(@Param("fromer") String fromer, @Param("receiver") String receiver);

    /**
     * 查询发件人邮箱是否存在客户联系人中
     * @param seaType
     * @param fromer
     * @param packetId
     * @return
     */
    int countCustomerContactByFromer(@Param("seaType") Integer seaType, @Param("fromer") String fromer, @Param("packetId") Long packetId);

    /**
     * 客户往来邮件列表
     * @param contactEmails
     * @return
     */
    List<DealingEmailListBO> dealingEmailList(@Param("contactEmails") List<String> contactEmails, @Param("taskIds") List<Long> taskIds);

    /**
     * 邮件详情
     * @param id
     * @return
     */
    EmailDetailVO detail(@Param("id") Long id);

    /**
     * 更新任务发送邮件状态
     * @param taskId
     */
    void updateTaskSendEmailStatus(@Param("taskId") Long taskId);

    /**
     * 查询未发送状态邮件
     * @param taskId
     * @return
     */
    List<TaskEmail> selectByUnSentStatus(@Param("taskId") Long taskId);
}
