package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskMapper;
import com.ruoyi.email.domain.MailboxTask;
import com.ruoyi.email.service.IMailboxTaskService;

/**
 * 邮箱任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskServiceImpl implements IMailboxTaskService 
{
    @Autowired
    private MailboxTaskMapper mailboxTaskMapper;

    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    @Override
    public MailboxTask selectMailboxTaskById(Long id)
    {
        return mailboxTaskMapper.selectMailboxTaskById(id);
    }

    /**
     * 查询邮箱任务列表
     * 
     * @param mailboxTask 邮箱任务
     * @return 邮箱任务
     */
    @Override
    public List<MailboxTask> selectMailboxTaskList(MailboxTask mailboxTask)
    {
        return mailboxTaskMapper.selectMailboxTaskList(mailboxTask);
    }

    /**
     * 新增邮箱任务
     * 
     * @param mailboxTask 邮箱任务
     * @return 结果
     */
    @Override
    public int insertMailboxTask(MailboxTask mailboxTask)
    {
        mailboxTask.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskMapper.insertMailboxTask(mailboxTask);
    }

    /**
     * 修改邮箱任务
     * 
     * @param mailboxTask 邮箱任务
     * @return 结果
     */
    @Override
    public int updateMailboxTask(MailboxTask mailboxTask)
    {
        mailboxTask.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskMapper.updateMailboxTask(mailboxTask);
    }

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskByIds(Long[] ids)
    {
        return mailboxTaskMapper.deleteMailboxTaskByIds(ids);
    }

    /**
     * 删除邮箱任务信息
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskById(Long id)
    {
        return mailboxTaskMapper.deleteMailboxTaskById(id);
    }
}
