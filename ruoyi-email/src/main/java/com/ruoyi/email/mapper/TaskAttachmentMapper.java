package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskAttachment;
import com.ruoyi.email.domain.bo.EmailAttachmentBO;
import com.ruoyi.email.domain.vo.attachment.AttachmentListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件附件Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskAttachmentMapper
{
    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    public TaskAttachment selectTaskEmailAttachmentById(Long id);

    /**
     * 查询邮件附件列表
     * 
     * @param taskAttachment 邮件附件
     * @return 邮件附件集合
     */
    public List<TaskAttachment> selectTaskEmailAttachmentList(TaskAttachment taskAttachment);

    /**
     * 新增邮件附件
     * 
     * @param taskAttachment 邮件附件
     * @return 结果
     */
    public int insertTaskEmailAttachment(TaskAttachment taskAttachment);

    /**
     * 修改邮件附件
     * 
     * @param taskAttachment 邮件附件
     * @return 结果
     */
    public int updateTaskEmailAttachment(TaskAttachment taskAttachment);

    /**
     * 删除邮件附件
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    public int deleteTaskEmailAttachmentById(Long id);

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailAttachmentByIds(Long[] ids);

    /**
     * 批量插入邮件附件
     * @param taskAttachmentList
     */
    void batchInsertTaskAttachment(@Param("taskAttachmentList") List<TaskAttachment> taskAttachmentList);

    /**
     * 根据邮件ID获取附件
     * @param emailId
     * @return
     */
    List<TaskAttachment> selectByEmailId(@Param("emailId") Long emailId);

    /**
     * 修改附件名称
     * @param name
     * @param id
     * @param userId
     */
    int updateNameById(@Param("name") String name, @Param("id") Long id, @Param("createId") Long userId);

    /**
     * 删除
     * @param id
     * @param userId
     * @return
     */
    int deleteById(@Param("id") Long id, @Param("createId") Long userId);

    /**
     * 根据id查询附件
     * @param id
     * @param userId
     * @return
     */
    TaskAttachment getById(@Param("id") Long id, @Param("createId") Long userId);

    /**
     * 根据邮件id查询附件
     * @param emailIds
     * @return
     */
    List<EmailAttachmentBO> selectByEmailIds(@Param("emailIds") List<Long> emailIds);
}
