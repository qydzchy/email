package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxBlacklist;

/**
 * 黑名单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxBlacklistMapper 
{
    /**
     * 查询黑名单
     * 
     * @param id 黑名单主键
     * @return 黑名单
     */
    public MailboxBlacklist selectMailboxBlacklistById(Long id);

    /**
     * 查询黑名单列表
     * 
     * @param mailboxBlacklist 黑名单
     * @return 黑名单集合
     */
    public List<MailboxBlacklist> selectMailboxBlacklistList(MailboxBlacklist mailboxBlacklist);

    /**
     * 新增黑名单
     * 
     * @param mailboxBlacklist 黑名单
     * @return 结果
     */
    public int insertMailboxBlacklist(MailboxBlacklist mailboxBlacklist);

    /**
     * 修改黑名单
     * 
     * @param mailboxBlacklist 黑名单
     * @return 结果
     */
    public int updateMailboxBlacklist(MailboxBlacklist mailboxBlacklist);

    /**
     * 删除黑名单
     * 
     * @param id 黑名单主键
     * @return 结果
     */
    public int deleteMailboxBlacklistById(Long id);

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxBlacklistByIds(Long[] ids);
}
