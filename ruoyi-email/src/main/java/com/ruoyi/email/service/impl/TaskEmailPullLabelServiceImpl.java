package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITaskEmailPullLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailPullLabelMapper;
import com.ruoyi.email.domain.TaskEmailPullLabel;

/**
 * 邮件标签Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskEmailPullLabelServiceImpl implements ITaskEmailPullLabelService
{
    @Autowired
    private TaskEmailPullLabelMapper taskEmailPullLabelMapper;

    /**
     * 查询邮件标签
     * 
     * @param id 邮件标签主键
     * @return 邮件标签
     */
    @Override
    public TaskEmailPullLabel selectTaskEmailPullLabelById(Long id)
    {
        return taskEmailPullLabelMapper.selectTaskEmailPullLabelById(id);
    }

    /**
     * 查询邮件标签列表
     * 
     * @param taskEmailPullLabel 邮件标签
     * @return 邮件标签
     */
    @Override
    public List<TaskEmailPullLabel> selectTaskEmailPullLabelList(TaskEmailPullLabel taskEmailPullLabel)
    {
        return taskEmailPullLabelMapper.selectTaskEmailPullLabelList(taskEmailPullLabel);
    }

    /**
     * 新增邮件标签
     * 
     * @param taskEmailPullLabel 邮件标签
     * @return 结果
     */
    @Override
    public int insertTaskEmailPullLabel(TaskEmailPullLabel taskEmailPullLabel)
    {
        taskEmailPullLabel.setCreateTime(DateUtils.getNowDate());
        return taskEmailPullLabelMapper.insertTaskEmailPullLabel(taskEmailPullLabel);
    }

    /**
     * 修改邮件标签
     * 
     * @param taskEmailPullLabel 邮件标签
     * @return 结果
     */
    @Override
    public int updateTaskEmailPullLabel(TaskEmailPullLabel taskEmailPullLabel)
    {
        taskEmailPullLabel.setUpdateTime(DateUtils.getNowDate());
        return taskEmailPullLabelMapper.updateTaskEmailPullLabel(taskEmailPullLabel);
    }

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的邮件标签主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullLabelByIds(Long[] ids)
    {
        return taskEmailPullLabelMapper.deleteTaskEmailPullLabelByIds(ids);
    }

    /**
     * 删除邮件标签信息
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullLabelById(Long id)
    {
        return taskEmailPullLabelMapper.deleteTaskEmailPullLabelById(id);
    }
}
