package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxImportEmail;

/**
 * 导入邮件Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxImportEmailMapper 
{
    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    public MailboxImportEmail selectMailboxImportEmailById(Long id);

    /**
     * 查询导入邮件列表
     * 
     * @param mailboxImportEmail 导入邮件
     * @return 导入邮件集合
     */
    public List<MailboxImportEmail> selectMailboxImportEmailList(MailboxImportEmail mailboxImportEmail);

    /**
     * 新增导入邮件
     * 
     * @param mailboxImportEmail 导入邮件
     * @return 结果
     */
    public int insertMailboxImportEmail(MailboxImportEmail mailboxImportEmail);

    /**
     * 修改导入邮件
     * 
     * @param mailboxImportEmail 导入邮件
     * @return 结果
     */
    public int updateMailboxImportEmail(MailboxImportEmail mailboxImportEmail);

    /**
     * 删除导入邮件
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    public int deleteMailboxImportEmailById(Long id);

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxImportEmailByIds(Long[] ids);
}
