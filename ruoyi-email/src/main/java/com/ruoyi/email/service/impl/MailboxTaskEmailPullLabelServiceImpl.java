package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailPullLabelMapper;
import com.ruoyi.email.domain.MailboxTaskEmailPullLabel;
import com.ruoyi.email.service.IMailboxTaskEmailPullLabelService;

/**
 * 邮件标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailPullLabelServiceImpl implements IMailboxTaskEmailPullLabelService 
{
    @Autowired
    private MailboxTaskEmailPullLabelMapper mailboxTaskEmailPullLabelMapper;

    /**
     * 查询邮件标签
     * 
     * @param id 邮件标签主键
     * @return 邮件标签
     */
    @Override
    public MailboxTaskEmailPullLabel selectMailboxTaskEmailPullLabelById(Long id)
    {
        return mailboxTaskEmailPullLabelMapper.selectMailboxTaskEmailPullLabelById(id);
    }

    /**
     * 查询邮件标签列表
     * 
     * @param mailboxTaskEmailPullLabel 邮件标签
     * @return 邮件标签
     */
    @Override
    public List<MailboxTaskEmailPullLabel> selectMailboxTaskEmailPullLabelList(MailboxTaskEmailPullLabel mailboxTaskEmailPullLabel)
    {
        return mailboxTaskEmailPullLabelMapper.selectMailboxTaskEmailPullLabelList(mailboxTaskEmailPullLabel);
    }

    /**
     * 新增邮件标签
     * 
     * @param mailboxTaskEmailPullLabel 邮件标签
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailPullLabel(MailboxTaskEmailPullLabel mailboxTaskEmailPullLabel)
    {
        mailboxTaskEmailPullLabel.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailPullLabelMapper.insertMailboxTaskEmailPullLabel(mailboxTaskEmailPullLabel);
    }

    /**
     * 修改邮件标签
     * 
     * @param mailboxTaskEmailPullLabel 邮件标签
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailPullLabel(MailboxTaskEmailPullLabel mailboxTaskEmailPullLabel)
    {
        mailboxTaskEmailPullLabel.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailPullLabelMapper.updateMailboxTaskEmailPullLabel(mailboxTaskEmailPullLabel);
    }

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的邮件标签主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailPullLabelByIds(Long[] ids)
    {
        return mailboxTaskEmailPullLabelMapper.deleteMailboxTaskEmailPullLabelByIds(ids);
    }

    /**
     * 删除邮件标签信息
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailPullLabelById(Long id)
    {
        return mailboxTaskEmailPullLabelMapper.deleteMailboxTaskEmailPullLabelById(id);
    }
}
