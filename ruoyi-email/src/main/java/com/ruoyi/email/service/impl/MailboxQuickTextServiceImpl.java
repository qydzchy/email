package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxQuickTextMapper;
import com.ruoyi.email.domain.MailboxQuickText;
import com.ruoyi.email.service.IMailboxQuickTextService;

/**
 * 快速文本Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxQuickTextServiceImpl implements IMailboxQuickTextService 
{
    @Autowired
    private MailboxQuickTextMapper mailboxQuickTextMapper;

    /**
     * 查询快速文本
     * 
     * @param id 快速文本主键
     * @return 快速文本
     */
    @Override
    public MailboxQuickText selectMailboxQuickTextById(Long id)
    {
        return mailboxQuickTextMapper.selectMailboxQuickTextById(id);
    }

    /**
     * 查询快速文本列表
     * 
     * @param mailboxQuickText 快速文本
     * @return 快速文本
     */
    @Override
    public List<MailboxQuickText> selectMailboxQuickTextList(MailboxQuickText mailboxQuickText)
    {
        return mailboxQuickTextMapper.selectMailboxQuickTextList(mailboxQuickText);
    }

    /**
     * 新增快速文本
     * 
     * @param mailboxQuickText 快速文本
     * @return 结果
     */
    @Override
    public int insertMailboxQuickText(MailboxQuickText mailboxQuickText)
    {
        mailboxQuickText.setCreateTime(DateUtils.getNowDate());
        return mailboxQuickTextMapper.insertMailboxQuickText(mailboxQuickText);
    }

    /**
     * 修改快速文本
     * 
     * @param mailboxQuickText 快速文本
     * @return 结果
     */
    @Override
    public int updateMailboxQuickText(MailboxQuickText mailboxQuickText)
    {
        mailboxQuickText.setUpdateTime(DateUtils.getNowDate());
        return mailboxQuickTextMapper.updateMailboxQuickText(mailboxQuickText);
    }

    /**
     * 批量删除快速文本
     * 
     * @param ids 需要删除的快速文本主键
     * @return 结果
     */
    @Override
    public int deleteMailboxQuickTextByIds(Long[] ids)
    {
        return mailboxQuickTextMapper.deleteMailboxQuickTextByIds(ids);
    }

    /**
     * 删除快速文本信息
     * 
     * @param id 快速文本主键
     * @return 结果
     */
    @Override
    public int deleteMailboxQuickTextById(Long id)
    {
        return mailboxQuickTextMapper.deleteMailboxQuickTextById(id);
    }
}
