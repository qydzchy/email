package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTemplateTypeMapper;
import com.ruoyi.email.domain.MailboxTemplateType;
import com.ruoyi.email.service.IMailboxTemplateTypeService;

/**
 * 模板类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTemplateTypeServiceImpl implements IMailboxTemplateTypeService 
{
    @Autowired
    private MailboxTemplateTypeMapper mailboxTemplateTypeMapper;

    /**
     * 查询模板类型
     * 
     * @param id 模板类型主键
     * @return 模板类型
     */
    @Override
    public MailboxTemplateType selectMailboxTemplateTypeById(Long id)
    {
        return mailboxTemplateTypeMapper.selectMailboxTemplateTypeById(id);
    }

    /**
     * 查询模板类型列表
     * 
     * @param mailboxTemplateType 模板类型
     * @return 模板类型
     */
    @Override
    public List<MailboxTemplateType> selectMailboxTemplateTypeList(MailboxTemplateType mailboxTemplateType)
    {
        return mailboxTemplateTypeMapper.selectMailboxTemplateTypeList(mailboxTemplateType);
    }

    /**
     * 新增模板类型
     * 
     * @param mailboxTemplateType 模板类型
     * @return 结果
     */
    @Override
    public int insertMailboxTemplateType(MailboxTemplateType mailboxTemplateType)
    {
        mailboxTemplateType.setCreateTime(DateUtils.getNowDate());
        return mailboxTemplateTypeMapper.insertMailboxTemplateType(mailboxTemplateType);
    }

    /**
     * 修改模板类型
     * 
     * @param mailboxTemplateType 模板类型
     * @return 结果
     */
    @Override
    public int updateMailboxTemplateType(MailboxTemplateType mailboxTemplateType)
    {
        mailboxTemplateType.setUpdateTime(DateUtils.getNowDate());
        return mailboxTemplateTypeMapper.updateMailboxTemplateType(mailboxTemplateType);
    }

    /**
     * 批量删除模板类型
     * 
     * @param ids 需要删除的模板类型主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTemplateTypeByIds(Long[] ids)
    {
        return mailboxTemplateTypeMapper.deleteMailboxTemplateTypeByIds(ids);
    }

    /**
     * 删除模板类型信息
     * 
     * @param id 模板类型主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTemplateTypeById(Long id)
    {
        return mailboxTemplateTypeMapper.deleteMailboxTemplateTypeById(id);
    }
}
