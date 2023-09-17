package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailTrace;

/**
 * 邮件追踪Mapper接口
 * 
 * @author tangJM.
 * @date 2023-09-17
 */
public interface TaskEmailTraceMapper 
{
    /**
     * 查询邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 邮件追踪
     */
    public TaskEmailTrace selectTaskEmailTraceById(Long id);

    /**
     * 查询邮件追踪列表
     * 
     * @param taskEmailTrace 邮件追踪
     * @return 邮件追踪集合
     */
    public List<TaskEmailTrace> selectTaskEmailTraceList(TaskEmailTrace taskEmailTrace);

    /**
     * 新增邮件追踪
     * 
     * @param taskEmailTrace 邮件追踪
     * @return 结果
     */
    public int insertTaskEmailTrace(TaskEmailTrace taskEmailTrace);

    /**
     * 修改邮件追踪
     * 
     * @param taskEmailTrace 邮件追踪
     * @return 结果
     */
    public int updateTaskEmailTrace(TaskEmailTrace taskEmailTrace);

    /**
     * 删除邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    public int deleteTaskEmailTraceById(Long id);

    /**
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailTraceByIds(Long[] ids);
}
