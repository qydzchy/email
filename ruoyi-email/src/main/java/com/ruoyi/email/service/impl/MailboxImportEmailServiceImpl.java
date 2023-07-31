package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxImportEmailMapper;
import com.ruoyi.email.domain.MailboxImportEmail;
import com.ruoyi.email.service.IMailboxImportEmailService;

/**
 * 导入邮件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxImportEmailServiceImpl implements IMailboxImportEmailService 
{
    @Autowired
    private MailboxImportEmailMapper mailboxImportEmailMapper;

    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    @Override
    public MailboxImportEmail selectMailboxImportEmailById(Long id)
    {
        return mailboxImportEmailMapper.selectMailboxImportEmailById(id);
    }

    /**
     * 查询导入邮件列表
     * 
     * @param mailboxImportEmail 导入邮件
     * @return 导入邮件
     */
    @Override
    public List<MailboxImportEmail> selectMailboxImportEmailList(MailboxImportEmail mailboxImportEmail)
    {
        return mailboxImportEmailMapper.selectMailboxImportEmailList(mailboxImportEmail);
    }

    /**
     * 新增导入邮件
     * 
     * @param mailboxImportEmail 导入邮件
     * @return 结果
     */
    @Override
    public int insertMailboxImportEmail(MailboxImportEmail mailboxImportEmail)
    {
        mailboxImportEmail.setCreateTime(DateUtils.getNowDate());
        return mailboxImportEmailMapper.insertMailboxImportEmail(mailboxImportEmail);
    }

    /**
     * 修改导入邮件
     * 
     * @param mailboxImportEmail 导入邮件
     * @return 结果
     */
    @Override
    public int updateMailboxImportEmail(MailboxImportEmail mailboxImportEmail)
    {
        mailboxImportEmail.setUpdateTime(DateUtils.getNowDate());
        return mailboxImportEmailMapper.updateMailboxImportEmail(mailboxImportEmail);
    }

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxImportEmailByIds(Long[] ids)
    {
        return mailboxImportEmailMapper.deleteMailboxImportEmailByIds(ids);
    }

    /**
     * 删除导入邮件信息
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxImportEmailById(Long id)
    {
        return mailboxImportEmailMapper.deleteMailboxImportEmailById(id);
    }
}
