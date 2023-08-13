package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.dto.EditTaskDTO;
import com.ruoyi.email.domain.vo.ListTaskVO;
import org.springframework.data.util.Pair;

/**
 * 邮箱任务Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskService 
{
    /**
     * 查询邮箱任务
     *
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    public Task selectTaskById(Long id);

    /**
     * 新增邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    public Boolean insertTask(Task task);

    /**
     * 修改邮箱任务
     * 
     * @param editTaskDTO 邮箱任务
     * @return 结果
     */
    public int updateTask(EditTaskDTO editTaskDTO);

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的邮箱任务主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 删除邮箱任务信息
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    public int deleteTaskById(Long id);

    /**
     * 邮箱检测
     * @param id
     * @return
     */
    Pair<Boolean, String> testTask(Long id);

    /**
     * 列表
     * @return
     */
    List<ListTaskVO> listTask();
}
