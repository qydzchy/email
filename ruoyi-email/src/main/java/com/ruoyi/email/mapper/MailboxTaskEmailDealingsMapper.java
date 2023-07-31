package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailDealings;

/**
 * 往来邮件Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTaskEmailDealingsMapper 
{
    /**
     * 查询往来邮件
     * 
     * @param id 往来邮件主键
     * @return 往来邮件
     */
    public MailboxTaskEmailDealings selectMailboxTaskEmailDealingsById(Long id);

    /**
     * 查询往来邮件列表
     * 
     * @param mailboxTaskEmailDealings 往来邮件
     * @return 往来邮件集合
     */
    public List<MailboxTaskEmailDealings> selectMailboxTaskEmailDealingsList(MailboxTaskEmailDealings mailboxTaskEmailDealings);

    /**
     * 新增往来邮件
     * 
     * @param mailboxTaskEmailDealings 往来邮件
     * @return 结果
     */
    public int insertMailboxTaskEmailDealings(MailboxTaskEmailDealings mailboxTaskEmailDealings);

    /**
     * 修改往来邮件
     * 
     * @param mailboxTaskEmailDealings 往来邮件
     * @return 结果
     */
    public int updateMailboxTaskEmailDealings(MailboxTaskEmailDealings mailboxTaskEmailDealings);

    /**
     * 删除往来邮件
     * 
     * @param id 往来邮件主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailDealingsById(Long id);

    /**
     * 批量删除往来邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailDealingsByIds(Long[] ids);
}
