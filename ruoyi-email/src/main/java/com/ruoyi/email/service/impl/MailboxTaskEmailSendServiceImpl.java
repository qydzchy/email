package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailSendMapper;
import com.ruoyi.email.domain.MailboxTaskEmailSend;
import com.ruoyi.email.service.IMailboxTaskEmailSendService;

/**
 * 发送邮件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailSendServiceImpl implements IMailboxTaskEmailSendService 
{
    @Autowired
    private MailboxTaskEmailSendMapper mailboxTaskEmailSendMapper;

    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    @Override
    public MailboxTaskEmailSend selectMailboxTaskEmailSendById(Long id)
    {
        return mailboxTaskEmailSendMapper.selectMailboxTaskEmailSendById(id);
    }

    /**
     * 查询发送邮件列表
     * 
     * @param mailboxTaskEmailSend 发送邮件
     * @return 发送邮件
     */
    @Override
    public List<MailboxTaskEmailSend> selectMailboxTaskEmailSendList(MailboxTaskEmailSend mailboxTaskEmailSend)
    {
        return mailboxTaskEmailSendMapper.selectMailboxTaskEmailSendList(mailboxTaskEmailSend);
    }

    /**
     * 新增发送邮件
     * 
     * @param mailboxTaskEmailSend 发送邮件
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailSend(MailboxTaskEmailSend mailboxTaskEmailSend)
    {
        mailboxTaskEmailSend.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailSendMapper.insertMailboxTaskEmailSend(mailboxTaskEmailSend);
    }

    /**
     * 修改发送邮件
     * 
     * @param mailboxTaskEmailSend 发送邮件
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailSend(MailboxTaskEmailSend mailboxTaskEmailSend)
    {
        mailboxTaskEmailSend.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailSendMapper.updateMailboxTaskEmailSend(mailboxTaskEmailSend);
    }

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的发送邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailSendByIds(Long[] ids)
    {
        return mailboxTaskEmailSendMapper.deleteMailboxTaskEmailSendByIds(ids);
    }

    /**
     * 删除发送邮件信息
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailSendById(Long id)
    {
        return mailboxTaskEmailSendMapper.deleteMailboxTaskEmailSendById(id);
    }
}
