package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxTemplate;

/**
 * 模板Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxTemplateService 
{
    /**
     * 查询模板
     * 
     * @param id 模板主键
     * @return 模板
     */
    public MailboxTemplate selectMailboxTemplateById(Long id);

    /**
     * 查询模板列表
     * 
     * @param mailboxTemplate 模板
     * @return 模板集合
     */
    public List<MailboxTemplate> selectMailboxTemplateList(MailboxTemplate mailboxTemplate);

    /**
     * 新增模板
     * 
     * @param mailboxTemplate 模板
     * @return 结果
     */
    public int insertMailboxTemplate(MailboxTemplate mailboxTemplate);

    /**
     * 修改模板
     * 
     * @param mailboxTemplate 模板
     * @return 结果
     */
    public int updateMailboxTemplate(MailboxTemplate mailboxTemplate);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板主键集合
     * @return 结果
     */
    public int deleteMailboxTemplateByIds(Long[] ids);

    /**
     * 删除模板信息
     * 
     * @param id 模板主键
     * @return 结果
     */
    public int deleteMailboxTemplateById(Long id);
}
