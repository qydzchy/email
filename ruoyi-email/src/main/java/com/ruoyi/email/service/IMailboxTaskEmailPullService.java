package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailPull;

/**
 * 拉取邮件Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxTaskEmailPullService 
{
    /**
     * 查询拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 拉取邮件
     */
    public MailboxTaskEmailPull selectMailboxTaskEmailPullById(Long id);

    /**
     * 查询拉取邮件列表
     * 
     * @param mailboxTaskEmailPull 拉取邮件
     * @return 拉取邮件集合
     */
    public List<MailboxTaskEmailPull> selectMailboxTaskEmailPullList(MailboxTaskEmailPull mailboxTaskEmailPull);

    /**
     * 新增拉取邮件
     * 
     * @param mailboxTaskEmailPull 拉取邮件
     * @return 结果
     */
    public int insertMailboxTaskEmailPull(MailboxTaskEmailPull mailboxTaskEmailPull);

    /**
     * 修改拉取邮件
     * 
     * @param mailboxTaskEmailPull 拉取邮件
     * @return 结果
     */
    public int updateMailboxTaskEmailPull(MailboxTaskEmailPull mailboxTaskEmailPull);

    /**
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的拉取邮件主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailPullByIds(Long[] ids);

    /**
     * 删除拉取邮件信息
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailPullById(Long id);
}
