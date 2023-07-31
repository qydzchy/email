package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailContentMapper;
import com.ruoyi.email.domain.MailboxTaskEmailContent;
import com.ruoyi.email.service.IMailboxTaskEmailContentService;

/**
 * 邮件内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailContentServiceImpl implements IMailboxTaskEmailContentService 
{
    @Autowired
    private MailboxTaskEmailContentMapper mailboxTaskEmailContentMapper;

    /**
     * 查询邮件内容
     * 
     * @param id 邮件内容主键
     * @return 邮件内容
     */
    @Override
    public MailboxTaskEmailContent selectMailboxTaskEmailContentById(Long id)
    {
        return mailboxTaskEmailContentMapper.selectMailboxTaskEmailContentById(id);
    }

    /**
     * 查询邮件内容列表
     * 
     * @param mailboxTaskEmailContent 邮件内容
     * @return 邮件内容
     */
    @Override
    public List<MailboxTaskEmailContent> selectMailboxTaskEmailContentList(MailboxTaskEmailContent mailboxTaskEmailContent)
    {
        return mailboxTaskEmailContentMapper.selectMailboxTaskEmailContentList(mailboxTaskEmailContent);
    }

    /**
     * 新增邮件内容
     * 
     * @param mailboxTaskEmailContent 邮件内容
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailContent(MailboxTaskEmailContent mailboxTaskEmailContent)
    {
        mailboxTaskEmailContent.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailContentMapper.insertMailboxTaskEmailContent(mailboxTaskEmailContent);
    }

    /**
     * 修改邮件内容
     * 
     * @param mailboxTaskEmailContent 邮件内容
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailContent(MailboxTaskEmailContent mailboxTaskEmailContent)
    {
        mailboxTaskEmailContent.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailContentMapper.updateMailboxTaskEmailContent(mailboxTaskEmailContent);
    }

    /**
     * 批量删除邮件内容
     * 
     * @param ids 需要删除的邮件内容主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailContentByIds(Long[] ids)
    {
        return mailboxTaskEmailContentMapper.deleteMailboxTaskEmailContentByIds(ids);
    }

    /**
     * 删除邮件内容信息
     * 
     * @param id 邮件内容主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailContentById(Long id)
    {
        return mailboxTaskEmailContentMapper.deleteMailboxTaskEmailContentById(id);
    }
}
