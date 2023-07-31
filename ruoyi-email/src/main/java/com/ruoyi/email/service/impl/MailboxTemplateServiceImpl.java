package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTemplateMapper;
import com.ruoyi.email.domain.MailboxTemplate;
import com.ruoyi.email.service.IMailboxTemplateService;

/**
 * 模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTemplateServiceImpl implements IMailboxTemplateService 
{
    @Autowired
    private MailboxTemplateMapper mailboxTemplateMapper;

    /**
     * 查询模板
     * 
     * @param id 模板主键
     * @return 模板
     */
    @Override
    public MailboxTemplate selectMailboxTemplateById(Long id)
    {
        return mailboxTemplateMapper.selectMailboxTemplateById(id);
    }

    /**
     * 查询模板列表
     * 
     * @param mailboxTemplate 模板
     * @return 模板
     */
    @Override
    public List<MailboxTemplate> selectMailboxTemplateList(MailboxTemplate mailboxTemplate)
    {
        return mailboxTemplateMapper.selectMailboxTemplateList(mailboxTemplate);
    }

    /**
     * 新增模板
     * 
     * @param mailboxTemplate 模板
     * @return 结果
     */
    @Override
    public int insertMailboxTemplate(MailboxTemplate mailboxTemplate)
    {
        mailboxTemplate.setCreateTime(DateUtils.getNowDate());
        return mailboxTemplateMapper.insertMailboxTemplate(mailboxTemplate);
    }

    /**
     * 修改模板
     * 
     * @param mailboxTemplate 模板
     * @return 结果
     */
    @Override
    public int updateMailboxTemplate(MailboxTemplate mailboxTemplate)
    {
        mailboxTemplate.setUpdateTime(DateUtils.getNowDate());
        return mailboxTemplateMapper.updateMailboxTemplate(mailboxTemplate);
    }

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTemplateByIds(Long[] ids)
    {
        return mailboxTemplateMapper.deleteMailboxTemplateByIds(ids);
    }

    /**
     * 删除模板信息
     * 
     * @param id 模板主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTemplateById(Long id)
    {
        return mailboxTemplateMapper.deleteMailboxTemplateById(id);
    }
}
