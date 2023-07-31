package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailAttachmentMapper;
import com.ruoyi.email.domain.MailboxTaskEmailAttachment;
import com.ruoyi.email.service.IMailboxTaskEmailAttachmentService;

/**
 * 邮件附件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailAttachmentServiceImpl implements IMailboxTaskEmailAttachmentService 
{
    @Autowired
    private MailboxTaskEmailAttachmentMapper mailboxTaskEmailAttachmentMapper;

    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    @Override
    public MailboxTaskEmailAttachment selectMailboxTaskEmailAttachmentById(Long id)
    {
        return mailboxTaskEmailAttachmentMapper.selectMailboxTaskEmailAttachmentById(id);
    }

    /**
     * 查询邮件附件列表
     * 
     * @param mailboxTaskEmailAttachment 邮件附件
     * @return 邮件附件
     */
    @Override
    public List<MailboxTaskEmailAttachment> selectMailboxTaskEmailAttachmentList(MailboxTaskEmailAttachment mailboxTaskEmailAttachment)
    {
        return mailboxTaskEmailAttachmentMapper.selectMailboxTaskEmailAttachmentList(mailboxTaskEmailAttachment);
    }

    /**
     * 新增邮件附件
     * 
     * @param mailboxTaskEmailAttachment 邮件附件
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailAttachment(MailboxTaskEmailAttachment mailboxTaskEmailAttachment)
    {
        mailboxTaskEmailAttachment.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailAttachmentMapper.insertMailboxTaskEmailAttachment(mailboxTaskEmailAttachment);
    }

    /**
     * 修改邮件附件
     * 
     * @param mailboxTaskEmailAttachment 邮件附件
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailAttachment(MailboxTaskEmailAttachment mailboxTaskEmailAttachment)
    {
        mailboxTaskEmailAttachment.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailAttachmentMapper.updateMailboxTaskEmailAttachment(mailboxTaskEmailAttachment);
    }

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的邮件附件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailAttachmentByIds(Long[] ids)
    {
        return mailboxTaskEmailAttachmentMapper.deleteMailboxTaskEmailAttachmentByIds(ids);
    }

    /**
     * 删除邮件附件信息
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailAttachmentById(Long id)
    {
        return mailboxTaskEmailAttachmentMapper.deleteMailboxTaskEmailAttachmentById(id);
    }
}
