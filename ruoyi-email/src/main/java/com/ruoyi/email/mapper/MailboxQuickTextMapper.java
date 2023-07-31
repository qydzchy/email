package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxQuickText;

/**
 * 快速文本Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxQuickTextMapper 
{
    /**
     * 查询快速文本
     * 
     * @param id 快速文本主键
     * @return 快速文本
     */
    public MailboxQuickText selectMailboxQuickTextById(Long id);

    /**
     * 查询快速文本列表
     * 
     * @param mailboxQuickText 快速文本
     * @return 快速文本集合
     */
    public List<MailboxQuickText> selectMailboxQuickTextList(MailboxQuickText mailboxQuickText);

    /**
     * 新增快速文本
     * 
     * @param mailboxQuickText 快速文本
     * @return 结果
     */
    public int insertMailboxQuickText(MailboxQuickText mailboxQuickText);

    /**
     * 修改快速文本
     * 
     * @param mailboxQuickText 快速文本
     * @return 结果
     */
    public int updateMailboxQuickText(MailboxQuickText mailboxQuickText);

    /**
     * 删除快速文本
     * 
     * @param id 快速文本主键
     * @return 结果
     */
    public int deleteMailboxQuickTextById(Long id);

    /**
     * 批量删除快速文本
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxQuickTextByIds(Long[] ids);
}
