package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.dto.task.EditTaskDTO;
import com.ruoyi.email.domain.vo.task.HomeListTaskVO;
import com.ruoyi.email.domain.vo.task.ListTaskVO;
import com.ruoyi.email.domain.vo.task.TestTaskVO;

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
    TestTaskVO testTask(Long id);

    /**
     * 列表
     * @return
     */
    List<ListTaskVO> listTask();

    /**
     * 解绑
     * @param id
     * @return
     */
    Boolean unbind(Long id);

    /**
     * 收件箱列表
     * @return
     */
    List<HomeListTaskVO> pullList();

    /**
     * 是否存在任务
     */
   boolean existById(Long id, Long createId);
}
