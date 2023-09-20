package com.ruoyi.email.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailAttachmentMapper;
import com.ruoyi.email.domain.TaskEmailAttachment;
import com.ruoyi.email.service.ITaskEmailAttachmentService;

/**
 * 附件Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-09-20
 */
@Service
public class TaskEmailAttachmentServiceImpl implements ITaskEmailAttachmentService 
{
    @Autowired
    private TaskEmailAttachmentMapper taskEmailAttachmentMapper;

    /**
     * 查询附件
     * 
     * @param id 附件主键
     * @return 附件
     */
    @Override
    public TaskEmailAttachment selectTaskEmailAttachmentById(Long id)
    {
        return taskEmailAttachmentMapper.selectTaskEmailAttachmentById(id);
    }

    /**
     * 查询附件列表
     * 
     * @param taskEmailAttachment 附件
     * @return 附件
     */
    @Override
    public List<TaskEmailAttachment> selectTaskEmailAttachmentList(TaskEmailAttachment taskEmailAttachment)
    {
        return taskEmailAttachmentMapper.selectTaskEmailAttachmentList(taskEmailAttachment);
    }

    /**
     * 新增附件
     * 
     * @param taskEmailAttachment 附件
     * @return 结果
     */
    @Override
    public int insertTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment)
    {
        return taskEmailAttachmentMapper.insertTaskEmailAttachment(taskEmailAttachment);
    }

    /**
     * 修改附件
     * 
     * @param taskEmailAttachment 附件
     * @return 结果
     */
    @Override
    public int updateTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment)
    {
        return taskEmailAttachmentMapper.updateTaskEmailAttachment(taskEmailAttachment);
    }

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailAttachmentByIds(Long[] ids)
    {
        return taskEmailAttachmentMapper.deleteTaskEmailAttachmentByIds(ids);
    }

    /**
     * 删除附件信息
     * 
     * @param id 附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailAttachmentById(Long id)
    {
        return taskEmailAttachmentMapper.deleteTaskEmailAttachmentById(id);
    }

    /**
     * 批量插入邮件附件数据
     * @param taskEmailAttachmentList
     */
    @Override
    public void batchInsertTaskEmailAttachment(List<TaskEmailAttachment> taskEmailAttachmentList) {
        taskEmailAttachmentMapper.batchInsertTaskEmailAttachment(taskEmailAttachmentList);
    }
}
