package com.ruoyi.email.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITaskEmailPullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailPullMapper;
import com.ruoyi.email.domain.TaskEmailPull;

/**
 * 拉取邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskEmailPullServiceImpl implements ITaskEmailPullService
{
    @Autowired
    private TaskEmailPullMapper taskEmailPullMapper;

    /**
     * 查询拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 拉取邮件
     */
    @Override
    public TaskEmailPull selectTaskEmailPullById(Long id)
    {
        return taskEmailPullMapper.selectTaskEmailPullById(id);
    }

    /**
     * 查询拉取邮件列表
     * 
     * @param taskEmailPull 拉取邮件
     * @return 拉取邮件
     */
    @Override
    public List<TaskEmailPull> selectTaskEmailPullList(TaskEmailPull taskEmailPull)
    {
        return taskEmailPullMapper.selectTaskEmailPullList(taskEmailPull);
    }

    /**
     * 新增拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmailPull(TaskEmailPull taskEmailPull)
    {
        taskEmailPull.setCreateTime(DateUtils.getNowDate());
        return taskEmailPullMapper.insertTaskEmailPull(taskEmailPull);
    }

    /**
     * 修改拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmailPull(TaskEmailPull taskEmailPull)
    {
        taskEmailPull.setUpdateTime(DateUtils.getNowDate());
        return taskEmailPullMapper.updateTaskEmailPull(taskEmailPull);
    }

    /**
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullByIds(Long[] ids)
    {
        return taskEmailPullMapper.deleteTaskEmailPullByIds(ids);
    }

    /**
     * 删除拉取邮件信息
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullById(Long id)
    {
        return taskEmailPullMapper.deleteTaskEmailPullById(id);
    }

    /**
     * 获取邮箱拉取的邮件数量
     * @param ids
     * @param userId
     * @return
     */
    @Override
    public Map<Long, Integer> getPullEmailQuantityByIds(List<Long> ids, Long userId) {
        return taskEmailPullMapper.getPullEmailQuantityByIds(ids, userId);
    }
}
