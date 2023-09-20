package com.ruoyi.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.TaskEmailAttachment;
import com.ruoyi.email.domain.dto.email.EmailQuickReplyDTO;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.email.EmailListVO;
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
    Pair<Integer, List<Map<String, List<EmailListVO>>>> list(List<Long> taskIdList, Integer type, Boolean readFlag, Boolean pendingFlag, Boolean spamFlag, String delFlag, Boolean draftsFlag, Boolean traceFlag, Boolean fixedFlag, Long folderId, Integer pageNum, Integer pageSize);

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
    boolean moveFolder(List<Long> ids, Long folderId);

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
    List<TaskEmailAttachment> uploadAttachment(Long id);
}
