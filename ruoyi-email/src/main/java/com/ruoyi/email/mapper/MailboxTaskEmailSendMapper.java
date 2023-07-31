package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailSend;

/**
 * 发送邮件Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTaskEmailSendMapper 
{
    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    public MailboxTaskEmailSend selectMailboxTaskEmailSendById(Long id);

    /**
     * 查询发送邮件列表
     * 
     * @param mailboxTaskEmailSend 发送邮件
     * @return 发送邮件集合
     */
    public List<MailboxTaskEmailSend> selectMailboxTaskEmailSendList(MailboxTaskEmailSend mailboxTaskEmailSend);

    /**
     * 新增发送邮件
     * 
     * @param mailboxTaskEmailSend 发送邮件
     * @return 结果
     */
    public int insertMailboxTaskEmailSend(MailboxTaskEmailSend mailboxTaskEmailSend);

    /**
     * 修改发送邮件
     * 
     * @param mailboxTaskEmailSend 发送邮件
     * @return 结果
     */
    public int updateMailboxTaskEmailSend(MailboxTaskEmailSend mailboxTaskEmailSend);

    /**
     * 删除发送邮件
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailSendById(Long id);

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailSendByIds(Long[] ids);
}
