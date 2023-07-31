package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailContent;

/**
 * 邮件内容Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxTaskEmailContentService 
{
    /**
     * 查询邮件内容
     * 
     * @param id 邮件内容主键
     * @return 邮件内容
     */
    public MailboxTaskEmailContent selectMailboxTaskEmailContentById(Long id);

    /**
     * 查询邮件内容列表
     * 
     * @param mailboxTaskEmailContent 邮件内容
     * @return 邮件内容集合
     */
    public List<MailboxTaskEmailContent> selectMailboxTaskEmailContentList(MailboxTaskEmailContent mailboxTaskEmailContent);

    /**
     * 新增邮件内容
     * 
     * @param mailboxTaskEmailContent 邮件内容
     * @return 结果
     */
    public int insertMailboxTaskEmailContent(MailboxTaskEmailContent mailboxTaskEmailContent);

    /**
     * 修改邮件内容
     * 
     * @param mailboxTaskEmailContent 邮件内容
     * @return 结果
     */
    public int updateMailboxTaskEmailContent(MailboxTaskEmailContent mailboxTaskEmailContent);

    /**
     * 批量删除邮件内容
     * 
     * @param ids 需要删除的邮件内容主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailContentByIds(Long[] ids);

    /**
     * 删除邮件内容信息
     * 
     * @param id 邮件内容主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailContentById(Long id);
}
