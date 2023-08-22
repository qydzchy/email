package com.ruoyi.email.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITaskEmailSendService;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailSendMapper;
import com.ruoyi.email.domain.TaskEmailSend;

import javax.annotation.Resource;

/**
 * 发送邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskEmailSendServiceImpl implements ITaskEmailSendService
{
    @Resource
    private TaskEmailSendMapper taskEmailSendMapper;

    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    @Override
    public TaskEmailSend selectTaskEmailSendById(Long id)
    {
        return taskEmailSendMapper.selectTaskEmailSendById(id);
    }

    /**
     * 查询发送邮件列表
     * 
     * @param taskEmailSend 发送邮件
     * @return 发送邮件
     */
    @Override
    public List<TaskEmailSend> selectTaskEmailSendList(TaskEmailSend taskEmailSend)
    {
        return taskEmailSendMapper.selectTaskEmailSendList(taskEmailSend);
    }

    /**
     * 新增发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmailSend(TaskEmailSend taskEmailSend)
    {
        taskEmailSend.setCreateTime(DateUtils.getNowDate());
        return taskEmailSendMapper.insertTaskEmailSend(taskEmailSend);
    }

    /**
     * 修改发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmailSend(TaskEmailSend taskEmailSend)
    {
        taskEmailSend.setUpdateTime(DateUtils.getNowDate());
        return taskEmailSendMapper.updateTaskEmailSend(taskEmailSend);
    }

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的发送邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailSendByIds(Long[] ids)
    {
        return taskEmailSendMapper.deleteTaskEmailSendByIds(ids);
    }

    /**
     * 删除发送邮件信息
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailSendById(Long id)
    {
        return taskEmailSendMapper.deleteTaskEmailSendById(id);
    }

}
