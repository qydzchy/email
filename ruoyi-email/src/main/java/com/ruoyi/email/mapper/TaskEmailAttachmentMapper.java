package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailAttachment;
import org.apache.ibatis.annotations.Param;

/**
 * 附件Mapper接口
 * 
 * @author tangJM.
 * @date 2023-09-20
 */
public interface TaskEmailAttachmentMapper 
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
     * 删除附件
     * 
     * @param id 附件主键
     * @return 结果
     */
    public int deleteTaskEmailAttachmentById(Long id);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailAttachmentByIds(Long[] ids);

    /**
     * 批量插入邮件附件数据
     * @param taskEmailAttachmentList
     */
    void batchInsertTaskEmailAttachment(@Param("taskEmailAttachmentList") List<TaskEmailAttachment> taskEmailAttachmentList);
}
