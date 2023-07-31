package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxBlacklistMapper;
import com.ruoyi.email.domain.MailboxBlacklist;
import com.ruoyi.email.service.IMailboxBlacklistService;

/**
 * 黑名单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxBlacklistServiceImpl implements IMailboxBlacklistService 
{
    @Autowired
    private MailboxBlacklistMapper mailboxBlacklistMapper;

    /**
     * 查询黑名单
     * 
     * @param id 黑名单主键
     * @return 黑名单
     */
    @Override
    public MailboxBlacklist selectMailboxBlacklistById(Long id)
    {
        return mailboxBlacklistMapper.selectMailboxBlacklistById(id);
    }

    /**
     * 查询黑名单列表
     * 
     * @param mailboxBlacklist 黑名单
     * @return 黑名单
     */
    @Override
    public List<MailboxBlacklist> selectMailboxBlacklistList(MailboxBlacklist mailboxBlacklist)
    {
        return mailboxBlacklistMapper.selectMailboxBlacklistList(mailboxBlacklist);
    }

    /**
     * 新增黑名单
     * 
     * @param mailboxBlacklist 黑名单
     * @return 结果
     */
    @Override
    public int insertMailboxBlacklist(MailboxBlacklist mailboxBlacklist)
    {
        mailboxBlacklist.setCreateTime(DateUtils.getNowDate());
        return mailboxBlacklistMapper.insertMailboxBlacklist(mailboxBlacklist);
    }

    /**
     * 修改黑名单
     * 
     * @param mailboxBlacklist 黑名单
     * @return 结果
     */
    @Override
    public int updateMailboxBlacklist(MailboxBlacklist mailboxBlacklist)
    {
        mailboxBlacklist.setUpdateTime(DateUtils.getNowDate());
        return mailboxBlacklistMapper.updateMailboxBlacklist(mailboxBlacklist);
    }

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的黑名单主键
     * @return 结果
     */
    @Override
    public int deleteMailboxBlacklistByIds(Long[] ids)
    {
        return mailboxBlacklistMapper.deleteMailboxBlacklistByIds(ids);
    }

    /**
     * 删除黑名单信息
     * 
     * @param id 黑名单主键
     * @return 结果
     */
    @Override
    public int deleteMailboxBlacklistById(Long id)
    {
        return mailboxBlacklistMapper.deleteMailboxBlacklistById(id);
    }
}
