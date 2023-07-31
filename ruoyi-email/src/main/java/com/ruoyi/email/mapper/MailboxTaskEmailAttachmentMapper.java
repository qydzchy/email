package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTaskEmailAttachment;

/**
 * 邮件附件Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTaskEmailAttachmentMapper 
{
    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    public MailboxTaskEmailAttachment selectMailboxTaskEmailAttachmentById(Long id);

    /**
     * 查询邮件附件列表
     * 
     * @param mailboxTaskEmailAttachment 邮件附件
     * @return 邮件附件集合
     */
    public List<MailboxTaskEmailAttachment> selectMailboxTaskEmailAttachmentList(MailboxTaskEmailAttachment mailboxTaskEmailAttachment);

    /**
     * 新增邮件附件
     * 
     * @param mailboxTaskEmailAttachment 邮件附件
     * @return 结果
     */
    public int insertMailboxTaskEmailAttachment(MailboxTaskEmailAttachment mailboxTaskEmailAttachment);

    /**
     * 修改邮件附件
     * 
     * @param mailboxTaskEmailAttachment 邮件附件
     * @return 结果
     */
    public int updateMailboxTaskEmailAttachment(MailboxTaskEmailAttachment mailboxTaskEmailAttachment);

    /**
     * 删除邮件附件
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    public int deleteMailboxTaskEmailAttachmentById(Long id);

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTaskEmailAttachmentByIds(Long[] ids);
}
