package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITaskEmailDealingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailDealingsMapper;
import com.ruoyi.email.domain.TaskEmailDealings;

/**
 * 往来邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskEmailDealingsServiceImpl implements ITaskEmailDealingsService
{
    @Autowired
    private TaskEmailDealingsMapper taskEmailDealingsMapper;

    /**
     * 查询往来邮件
     * 
     * @param id 往来邮件主键
     * @return 往来邮件
     */
    @Override
    public TaskEmailDealings selectTaskEmailDealingsById(Long id)
    {
        return taskEmailDealingsMapper.selectTaskEmailDealingsById(id);
    }

    /**
     * 查询往来邮件列表
     * 
     * @param taskEmailDealings 往来邮件
     * @return 往来邮件
     */
    @Override
    public List<TaskEmailDealings> selectTaskEmailDealingsList(TaskEmailDealings taskEmailDealings)
    {
        return taskEmailDealingsMapper.selectTaskEmailDealingsList(taskEmailDealings);
    }

    /**
     * 新增往来邮件
     * 
     * @param taskEmailDealings 往来邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmailDealings(TaskEmailDealings taskEmailDealings)
    {
        taskEmailDealings.setCreateTime(DateUtils.getNowDate());
        return taskEmailDealingsMapper.insertTaskEmailDealings(taskEmailDealings);
    }

    /**
     * 修改往来邮件
     * 
     * @param taskEmailDealings 往来邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmailDealings(TaskEmailDealings taskEmailDealings)
    {
        taskEmailDealings.setUpdateTime(DateUtils.getNowDate());
        return taskEmailDealingsMapper.updateTaskEmailDealings(taskEmailDealings);
    }

    /**
     * 批量删除往来邮件
     * 
     * @param ids 需要删除的往来邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailDealingsByIds(Long[] ids)
    {
        return taskEmailDealingsMapper.deleteTaskEmailDealingsByIds(ids);
    }

    /**
     * 删除往来邮件信息
     * 
     * @param id 往来邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailDealingsById(Long id)
    {
        return taskEmailDealingsMapper.deleteTaskEmailDealingsById(id);
    }
}
