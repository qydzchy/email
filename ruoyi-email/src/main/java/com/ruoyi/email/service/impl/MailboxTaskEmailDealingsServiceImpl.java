package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailDealingsMapper;
import com.ruoyi.email.domain.MailboxTaskEmailDealings;
import com.ruoyi.email.service.IMailboxTaskEmailDealingsService;

/**
 * 往来邮件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailDealingsServiceImpl implements IMailboxTaskEmailDealingsService 
{
    @Autowired
    private MailboxTaskEmailDealingsMapper mailboxTaskEmailDealingsMapper;

    /**
     * 查询往来邮件
     * 
     * @param id 往来邮件主键
     * @return 往来邮件
     */
    @Override
    public MailboxTaskEmailDealings selectMailboxTaskEmailDealingsById(Long id)
    {
        return mailboxTaskEmailDealingsMapper.selectMailboxTaskEmailDealingsById(id);
    }

    /**
     * 查询往来邮件列表
     * 
     * @param mailboxTaskEmailDealings 往来邮件
     * @return 往来邮件
     */
    @Override
    public List<MailboxTaskEmailDealings> selectMailboxTaskEmailDealingsList(MailboxTaskEmailDealings mailboxTaskEmailDealings)
    {
        return mailboxTaskEmailDealingsMapper.selectMailboxTaskEmailDealingsList(mailboxTaskEmailDealings);
    }

    /**
     * 新增往来邮件
     * 
     * @param mailboxTaskEmailDealings 往来邮件
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailDealings(MailboxTaskEmailDealings mailboxTaskEmailDealings)
    {
        mailboxTaskEmailDealings.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailDealingsMapper.insertMailboxTaskEmailDealings(mailboxTaskEmailDealings);
    }

    /**
     * 修改往来邮件
     * 
     * @param mailboxTaskEmailDealings 往来邮件
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailDealings(MailboxTaskEmailDealings mailboxTaskEmailDealings)
    {
        mailboxTaskEmailDealings.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailDealingsMapper.updateMailboxTaskEmailDealings(mailboxTaskEmailDealings);
    }

    /**
     * 批量删除往来邮件
     * 
     * @param ids 需要删除的往来邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailDealingsByIds(Long[] ids)
    {
        return mailboxTaskEmailDealingsMapper.deleteMailboxTaskEmailDealingsByIds(ids);
    }

    /**
     * 删除往来邮件信息
     * 
     * @param id 往来邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailDealingsById(Long id)
    {
        return mailboxTaskEmailDealingsMapper.deleteMailboxTaskEmailDealingsById(id);
    }
}
