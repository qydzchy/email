package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailAttachment;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件附件Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskEmailAttachmentMapper
{
    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    public TaskEmailAttachment selectTaskEmailAttachmentById(Long id);

    /**
     * 查询邮件附件列表
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 邮件附件集合
     */
    public List<TaskEmailAttachment> selectTaskEmailAttachmentList(TaskEmailAttachment taskEmailAttachment);

    /**
     * 新增邮件附件
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 结果
     */
    public int insertTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment);

    /**
     * 修改邮件附件
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 结果
     */
    public int updateTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment);

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
     * @param emailAttachments
     */
    void batchInsertTaskEmailAttachment(List<TaskEmailAttachment> emailAttachments);

    /**
     * 根据id更新emailId
     * @param attachmentIdList
     */
    void updateEmailIdByIds(@Param("emailId") Long emailId, @Param("attachmentIdList") List<Long> attachmentIdList);

    /**
     * 根据邮件ID获取附件
     * @param emailId
     * @return
     */
    List<TaskEmailAttachment> selectByEmailId(@Param("emailId") Long emailId);
}
