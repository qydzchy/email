package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxLabel;

/**
 * 标签Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxLabelMapper 
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public MailboxLabel selectMailboxLabelById(Long id);

    /**
     * 查询标签列表
     * 
     * @param mailboxLabel 标签
     * @return 标签集合
     */
    public List<MailboxLabel> selectMailboxLabelList(MailboxLabel mailboxLabel);

    /**
     * 新增标签
     * 
     * @param mailboxLabel 标签
     * @return 结果
     */
    public int insertMailboxLabel(MailboxLabel mailboxLabel);

    /**
     * 修改标签
     * 
     * @param mailboxLabel 标签
     * @return 结果
     */
    public int updateMailboxLabel(MailboxLabel mailboxLabel);

    /**
     * 删除标签
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteMailboxLabelById(Long id);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxLabelByIds(Long[] ids);
}
