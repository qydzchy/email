package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.Task;

/**
 * 邮箱任务Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskMapper 
{
    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    public Task selectTaskById(Long id);

    /**
     * 查询邮箱任务列表
     * 
     * @param task 邮箱任务
     * @return 邮箱任务集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    public int deleteTaskById(Long id);

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);
}
