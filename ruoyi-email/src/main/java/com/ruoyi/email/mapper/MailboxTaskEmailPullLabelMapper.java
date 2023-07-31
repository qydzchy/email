package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailPullLabel;

/**
 * 邮件标签Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTaskEmailPullLabelMapper 
{
    /**
     * 查询邮件标签
     * 
     * @param id 邮件标签主键
     * @return 邮件标签
     */
    public MailboxTaskEmailPullLabel selectMailboxTaskEmailPullLabelById(Long id);

    /**
     * 查询邮件标签列表
     * 
     * @param mailboxTaskEmailPullLabel 邮件标签
     * @return 邮件标签集合
     */
    public List<MailboxTaskEmailPullLabel> selectMailboxTaskEmailPullLabelList(MailboxTaskEmailPullLabel mailboxTaskEmailPullLabel);

    /**
     * 新增邮件标签
     * 
     * @param mailboxTaskEmailPullLabel 邮件标签
     * @return 结果
     */
    public int insertMailboxTaskEmailPullLabel(MailboxTaskEmailPullLabel mailboxTaskEmailPullLabel);

    /**
     * 修改邮件标签
     * 
     * @param mailboxTaskEmailPullLabel 邮件标签
     * @return 结果
     */
    public int updateMailboxTaskEmailPullLabel(MailboxTaskEmailPullLabel mailboxTaskEmailPullLabel);

    /**
     * 删除邮件标签
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailPullLabelById(Long id);

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailPullLabelByIds(Long[] ids);
}
