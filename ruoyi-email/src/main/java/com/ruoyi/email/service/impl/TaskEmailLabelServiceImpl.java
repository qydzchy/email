package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailLabelMapper;
import com.ruoyi.email.domain.TaskEmailLabel;
import com.ruoyi.email.service.ITaskEmailLabelService;

/**
 * 邮件标签Service业务层处理
 * 
 * @author tangJM
 * @date 2023-10-10
 */
@Service
public class TaskEmailLabelServiceImpl implements ITaskEmailLabelService 
{
    @Autowired
    private TaskEmailLabelMapper taskEmailLabelMapper;

    /**
     * 查询邮件标签
     * 
     * @param id 邮件标签主键
     * @return 邮件标签
     */
    @Override
    public TaskEmailLabel selectTaskEmailLabelById(Long id)
    {
        return taskEmailLabelMapper.selectTaskEmailLabelById(id);
    }

    /**
     * 查询邮件标签列表
     * 
     * @param taskEmailLabel 邮件标签
     * @return 邮件标签
     */
    @Override
    public List<TaskEmailLabel> selectTaskEmailLabelList(TaskEmailLabel taskEmailLabel)
    {
        return taskEmailLabelMapper.selectTaskEmailLabelList(taskEmailLabel);
    }

    /**
     * 新增邮件标签
     * 
     * @param taskEmailLabel 邮件标签
     * @return 结果
     */
    @Override
    public int insertTaskEmailLabel(TaskEmailLabel taskEmailLabel)
    {
        taskEmailLabel.setCreateTime(DateUtils.getNowDate());
        return taskEmailLabelMapper.insertTaskEmailLabel(taskEmailLabel);
    }

    /**
     * 修改邮件标签
     * 
     * @param taskEmailLabel 邮件标签
     * @return 结果
     */
    @Override
    public int updateTaskEmailLabel(TaskEmailLabel taskEmailLabel)
    {
        taskEmailLabel.setUpdateTime(DateUtils.getNowDate());
        return taskEmailLabelMapper.updateTaskEmailLabel(taskEmailLabel);
    }

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的邮件标签主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailLabelByIds(Long[] ids)
    {
        return taskEmailLabelMapper.deleteTaskEmailLabelByIds(ids);
    }

    /**
     * 删除邮件标签信息
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailLabelById(Long id)
    {
        return taskEmailLabelMapper.deleteTaskEmailLabelById(id);
    }

    /**
     * 根据邮件id和标签id删除
     * @param emailId
     * @param labelId
     */
    @Override
    public void deleteByEmailIdAndLabelId(Long emailId, Long labelId, Long userId) {
        taskEmailLabelMapper.deleteByEmailIdAndLabelId(emailId, labelId, userId);
    }
}
