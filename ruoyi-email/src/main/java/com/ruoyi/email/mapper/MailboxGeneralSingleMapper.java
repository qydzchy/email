package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxGeneralSingle;

/**
 * 单个邮箱设置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxGeneralSingleMapper 
{
    /**
     * 查询单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 单个邮箱设置
     */
    public MailboxGeneralSingle selectMailboxGeneralSingleById(Long id);

    /**
     * 查询单个邮箱设置列表
     * 
     * @param mailboxGeneralSingle 单个邮箱设置
     * @return 单个邮箱设置集合
     */
    public List<MailboxGeneralSingle> selectMailboxGeneralSingleList(MailboxGeneralSingle mailboxGeneralSingle);

    /**
     * 新增单个邮箱设置
     * 
     * @param mailboxGeneralSingle 单个邮箱设置
     * @return 结果
     */
    public int insertMailboxGeneralSingle(MailboxGeneralSingle mailboxGeneralSingle);

    /**
     * 修改单个邮箱设置
     * 
     * @param mailboxGeneralSingle 单个邮箱设置
     * @return 结果
     */
    public int updateMailboxGeneralSingle(MailboxGeneralSingle mailboxGeneralSingle);

    /**
     * 删除单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 结果
     */
    public int deleteMailboxGeneralSingleById(Long id);

    /**
     * 批量删除单个邮箱设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxGeneralSingleByIds(Long[] ids);
}
