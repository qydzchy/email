package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailSendTraceMapper;
import com.ruoyi.email.domain.MailboxTaskEmailSendTrace;
import com.ruoyi.email.service.IMailboxTaskEmailSendTraceService;

/**
 * 邮件追踪Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailSendTraceServiceImpl implements IMailboxTaskEmailSendTraceService 
{
    @Autowired
    private MailboxTaskEmailSendTraceMapper mailboxTaskEmailSendTraceMapper;

    /**
     * 查询邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 邮件追踪
     */
    @Override
    public MailboxTaskEmailSendTrace selectMailboxTaskEmailSendTraceById(Long id)
    {
        return mailboxTaskEmailSendTraceMapper.selectMailboxTaskEmailSendTraceById(id);
    }

    /**
     * 查询邮件追踪列表
     * 
     * @param mailboxTaskEmailSendTrace 邮件追踪
     * @return 邮件追踪
     */
    @Override
    public List<MailboxTaskEmailSendTrace> selectMailboxTaskEmailSendTraceList(MailboxTaskEmailSendTrace mailboxTaskEmailSendTrace)
    {
        return mailboxTaskEmailSendTraceMapper.selectMailboxTaskEmailSendTraceList(mailboxTaskEmailSendTrace);
    }

    /**
     * 新增邮件追踪
     * 
     * @param mailboxTaskEmailSendTrace 邮件追踪
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailSendTrace(MailboxTaskEmailSendTrace mailboxTaskEmailSendTrace)
    {
        mailboxTaskEmailSendTrace.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailSendTraceMapper.insertMailboxTaskEmailSendTrace(mailboxTaskEmailSendTrace);
    }

    /**
     * 修改邮件追踪
     * 
     * @param mailboxTaskEmailSendTrace 邮件追踪
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailSendTrace(MailboxTaskEmailSendTrace mailboxTaskEmailSendTrace)
    {
        mailboxTaskEmailSendTrace.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailSendTraceMapper.updateMailboxTaskEmailSendTrace(mailboxTaskEmailSendTrace);
    }

    /**
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的邮件追踪主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailSendTraceByIds(Long[] ids)
    {
        return mailboxTaskEmailSendTraceMapper.deleteMailboxTaskEmailSendTraceByIds(ids);
    }

    /**
     * 删除邮件追踪信息
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailSendTraceById(Long id)
    {
        return mailboxTaskEmailSendTraceMapper.deleteMailboxTaskEmailSendTraceById(id);
    }
}
