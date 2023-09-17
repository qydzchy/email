package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailTrace;

/**
 * 邮件追踪Service接口
 * 
 * @author tangJM.
 * @date 2023-09-17
 */
public interface ITaskEmailTraceService 
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
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的邮件追踪主键集合
     * @return 结果
     */
    public int deleteTaskEmailTraceByIds(Long[] ids);

    /**
     * 删除邮件追踪信息
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    public int deleteTaskEmailTraceById(Long id);

    /**
     * 获取ip位置保存
     * @param id
     * @param ipAddr
     */
    void getLocationToSave(Long id, String ipAddr);
}
