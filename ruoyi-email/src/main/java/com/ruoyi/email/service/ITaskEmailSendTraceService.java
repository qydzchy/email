package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailSendTrace;

/**
 * 邮件追踪Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskEmailSendTraceService
{
    /**
     * 查询邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 邮件追踪
     */
    public TaskEmailSendTrace selectTaskEmailSendTraceById(Long id);

    /**
     * 查询邮件追踪列表
     * 
     * @param taskEmailSendTrace 邮件追踪
     * @return 邮件追踪集合
     */
    public List<TaskEmailSendTrace> selectTaskEmailSendTraceList(TaskEmailSendTrace taskEmailSendTrace);

    /**
     * 新增邮件追踪
     * 
     * @param taskEmailSendTrace 邮件追踪
     * @return 结果
     */
    public int insertTaskEmailSendTrace(TaskEmailSendTrace taskEmailSendTrace);

    /**
     * 修改邮件追踪
     * 
     * @param taskEmailSendTrace 邮件追踪
     * @return 结果
     */
    public int updateTaskEmailSendTrace(TaskEmailSendTrace taskEmailSendTrace);

    /**
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的邮件追踪主键集合
     * @return 结果
     */
    public int deleteTaskEmailSendTraceByIds(Long[] ids);

    /**
     * 删除邮件追踪信息
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    public int deleteTaskEmailSendTraceById(Long id);
}
