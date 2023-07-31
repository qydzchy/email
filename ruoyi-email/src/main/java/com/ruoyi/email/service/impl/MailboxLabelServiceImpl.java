package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxLabelMapper;
import com.ruoyi.email.domain.MailboxLabel;
import com.ruoyi.email.service.IMailboxLabelService;

/**
 * 标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxLabelServiceImpl implements IMailboxLabelService 
{
    @Autowired
    private MailboxLabelMapper mailboxLabelMapper;

    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public MailboxLabel selectMailboxLabelById(Long id)
    {
        return mailboxLabelMapper.selectMailboxLabelById(id);
    }

    /**
     * 查询标签列表
     * 
     * @param mailboxLabel 标签
     * @return 标签
     */
    @Override
    public List<MailboxLabel> selectMailboxLabelList(MailboxLabel mailboxLabel)
    {
        return mailboxLabelMapper.selectMailboxLabelList(mailboxLabel);
    }

    /**
     * 新增标签
     * 
     * @param mailboxLabel 标签
     * @return 结果
     */
    @Override
    public int insertMailboxLabel(MailboxLabel mailboxLabel)
    {
        mailboxLabel.setCreateTime(DateUtils.getNowDate());
        return mailboxLabelMapper.insertMailboxLabel(mailboxLabel);
    }

    /**
     * 修改标签
     * 
     * @param mailboxLabel 标签
     * @return 结果
     */
    @Override
    public int updateMailboxLabel(MailboxLabel mailboxLabel)
    {
        mailboxLabel.setUpdateTime(DateUtils.getNowDate());
        return mailboxLabelMapper.updateMailboxLabel(mailboxLabel);
    }

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteMailboxLabelByIds(Long[] ids)
    {
        return mailboxLabelMapper.deleteMailboxLabelByIds(ids);
    }

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteMailboxLabelById(Long id)
    {
        return mailboxLabelMapper.deleteMailboxLabelById(id);
    }
}
