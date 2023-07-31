package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxTemplate;

/**
 * 模板Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxTemplateMapper 
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
     * 删除模板
     * 
     * @param id 模板主键
     * @return 结果
     */
    public int deleteMailboxTemplateById(Long id);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxTemplateByIds(Long[] ids);
}
