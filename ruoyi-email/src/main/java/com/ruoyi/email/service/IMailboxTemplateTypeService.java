package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxTemplateType;

/**
 * 模板类型Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxTemplateTypeService 
{
    /**
     * 查询模板类型
     * 
     * @param id 模板类型主键
     * @return 模板类型
     */
    public MailboxTemplateType selectMailboxTemplateTypeById(Long id);

    /**
     * 查询模板类型列表
     * 
     * @param mailboxTemplateType 模板类型
     * @return 模板类型集合
     */
    public List<MailboxTemplateType> selectMailboxTemplateTypeList(MailboxTemplateType mailboxTemplateType);

    /**
     * 新增模板类型
     * 
     * @param mailboxTemplateType 模板类型
     * @return 结果
     */
    public int insertMailboxTemplateType(MailboxTemplateType mailboxTemplateType);

    /**
     * 修改模板类型
     * 
     * @param mailboxTemplateType 模板类型
     * @return 结果
     */
    public int updateMailboxTemplateType(MailboxTemplateType mailboxTemplateType);

    /**
     * 批量删除模板类型
     * 
     * @param ids 需要删除的模板类型主键集合
     * @return 结果
     */
    public int deleteMailboxTemplateTypeByIds(Long[] ids);

    /**
     * 删除模板类型信息
     * 
     * @param id 模板类型主键
     * @return 结果
     */
    public int deleteMailboxTemplateTypeById(Long id);
}
