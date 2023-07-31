package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailSendTrace;

/**
 * 邮件追踪Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTaskEmailSendTraceMapper 
{
    /**
     * 查询邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 邮件追踪
     */
    public MailboxTaskEmailSendTrace selectMailboxTaskEmailSendTraceById(Long id);

    /**
     * 查询邮件追踪列表
     * 
     * @param mailboxTaskEmailSendTrace 邮件追踪
     * @return 邮件追踪集合
     */
    public List<MailboxTaskEmailSendTrace> selectMailboxTaskEmailSendTraceList(MailboxTaskEmailSendTrace mailboxTaskEmailSendTrace);

    /**
     * 新增邮件追踪
     * 
     * @param mailboxTaskEmailSendTrace 邮件追踪
     * @return 结果
     */
    public int insertMailboxTaskEmailSendTrace(MailboxTaskEmailSendTrace mailboxTaskEmailSendTrace);

    /**
     * 修改邮件追踪
     * 
     * @param mailboxTaskEmailSendTrace 邮件追踪
     * @return 结果
     */
    public int updateMailboxTaskEmailSendTrace(MailboxTaskEmailSendTrace mailboxTaskEmailSendTrace);

    /**
     * 删除邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailSendTraceById(Long id);

    /**
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailSendTraceByIds(Long[] ids);
}
