package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTask;

/**
 * 邮箱任务Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTaskMapper 
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
     * 删除邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    public int deleteMailboxTaskById(Long id);

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTaskByIds(Long[] ids);
}
