package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxTask;

/**
 * 邮箱任务Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxTaskService 
{
    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    public MailboxTask selectMailboxTaskById(Long id);

    /**
     * 查询邮箱任务列表
     * 
     * @param mailboxTask 邮箱任务
     * @return 邮箱任务集合
     */
    public List<MailboxTask> selectMailboxTaskList(MailboxTask mailboxTask);

    /**
     * 新增邮箱任务
     * 
     * @param mailboxTask 邮箱任务
     * @return 结果
     */
    public int insertMailboxTask(MailboxTask mailboxTask);

    /**
     * 修改邮箱任务
     * 
     * @param mailboxTask 邮箱任务
     * @return 结果
     */
    public int updateMailboxTask(MailboxTask mailboxTask);

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的邮箱任务主键集合
     * @return 结果
     */
    public int deleteMailboxTaskByIds(Long[] ids);

    /**
     * 删除邮箱任务信息
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    public int deleteMailboxTaskById(Long id);
}
