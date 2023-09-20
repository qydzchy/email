package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailAttachment;

/**
 * 附件Service接口
 * 
 * @author tangJM.
 * @date 2023-09-20
 */
public interface ITaskEmailAttachmentService 
{
    /**
     * 查询附件
     * 
     * @param id 附件主键
     * @return 附件
     */
    public TaskEmailAttachment selectTaskEmailAttachmentById(Long id);

    /**
     * 查询附件列表
     * 
     * @param taskEmailAttachment 附件
     * @return 附件集合
     */
    public List<TaskEmailAttachment> selectTaskEmailAttachmentList(TaskEmailAttachment taskEmailAttachment);

    /**
     * 新增附件
     * 
     * @param taskEmailAttachment 附件
     * @return 结果
     */
    public int insertTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment);

    /**
     * 修改附件
     * 
     * @param taskEmailAttachment 附件
     * @return 结果
     */
    public int updateTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的附件主键集合
     * @return 结果
     */
    public int deleteTaskEmailAttachmentByIds(Long[] ids);

    /**
     * 删除附件信息
     * 
     * @param id 附件主键
     * @return 结果
     */
    public int deleteTaskEmailAttachmentById(Long id);

    void batchInsertTaskEmailAttachment(List<TaskEmailAttachment> taskEmailAttachmentList);
}
