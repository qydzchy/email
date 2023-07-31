package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITaskEmailSendTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailSendTraceMapper;
import com.ruoyi.email.domain.TaskEmailSendTrace;

/**
 * 邮件追踪Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskEmailSendTraceServiceImpl implements ITaskEmailSendTraceService
{
    @Autowired
    private TaskEmailSendTraceMapper taskEmailSendTraceMapper;

    /**
     * 查询邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 邮件追踪
     */
    @Override
    public TaskEmailSendTrace selectTaskEmailSendTraceById(Long id)
    {
        return taskEmailSendTraceMapper.selectTaskEmailSendTraceById(id);
    }

    /**
     * 查询邮件追踪列表
     * 
     * @param taskEmailSendTrace 邮件追踪
     * @return 邮件追踪
     */
    @Override
    public List<TaskEmailSendTrace> selectTaskEmailSendTraceList(TaskEmailSendTrace taskEmailSendTrace)
    {
        return taskEmailSendTraceMapper.selectTaskEmailSendTraceList(taskEmailSendTrace);
    }

    /**
     * 新增邮件追踪
     * 
     * @param taskEmailSendTrace 邮件追踪
     * @return 结果
     */
    @Override
    public int insertTaskEmailSendTrace(TaskEmailSendTrace taskEmailSendTrace)
    {
        taskEmailSendTrace.setCreateTime(DateUtils.getNowDate());
        return taskEmailSendTraceMapper.insertTaskEmailSendTrace(taskEmailSendTrace);
    }

    /**
     * 修改邮件追踪
     * 
     * @param taskEmailSendTrace 邮件追踪
     * @return 结果
     */
    @Override
    public int updateTaskEmailSendTrace(TaskEmailSendTrace taskEmailSendTrace)
    {
        taskEmailSendTrace.setUpdateTime(DateUtils.getNowDate());
        return taskEmailSendTraceMapper.updateTaskEmailSendTrace(taskEmailSendTrace);
    }

    /**
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的邮件追踪主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailSendTraceByIds(Long[] ids)
    {
        return taskEmailSendTraceMapper.deleteTaskEmailSendTraceByIds(ids);
    }

    /**
     * 删除邮件追踪信息
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailSendTraceById(Long id)
    {
        return taskEmailSendTraceMapper.deleteTaskEmailSendTraceById(id);
    }
}
