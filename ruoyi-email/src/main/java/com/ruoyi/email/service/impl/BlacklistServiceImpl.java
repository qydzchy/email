package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.BlacklistMapper;
import com.ruoyi.email.domain.Blacklist;
import com.ruoyi.email.service.IBlacklistService;

/**
 * 黑名单Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class BlacklistServiceImpl implements IBlacklistService 
{
    @Autowired
    private BlacklistMapper blacklistMapper;

    /**
     * 查询黑名单
     * 
     * @param id 黑名单主键
     * @return 黑名单
     */
    @Override
    public Blacklist selectBlacklistById(Long id)
    {
        return blacklistMapper.selectBlacklistById(id);
    }

    /**
     * 查询黑名单列表
     * 
     * @param blacklist 黑名单
     * @return 黑名单
     */
    @Override
    public List<Blacklist> selectBlacklistList(Blacklist blacklist)
    {
        return blacklistMapper.selectBlacklistList(blacklist);
    }

    /**
     * 新增黑名单
     * 
     * @param blacklist 黑名单
     * @return 结果
     */
    @Override
    public int insertBlacklist(Blacklist blacklist)
    {
        blacklist.setCreateTime(DateUtils.getNowDate());
        return blacklistMapper.insertBlacklist(blacklist);
    }

    /**
     * 修改黑名单
     * 
     * @param blacklist 黑名单
     * @return 结果
     */
    @Override
    public int updateBlacklist(Blacklist blacklist)
    {
        blacklist.setUpdateTime(DateUtils.getNowDate());
        return blacklistMapper.updateBlacklist(blacklist);
    }

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的黑名单主键
     * @return 结果
     */
    @Override
    public int deleteBlacklistByIds(Long[] ids)
    {
        return blacklistMapper.deleteBlacklistByIds(ids);
    }

    /**
     * 删除黑名单信息
     * 
     * @param id 黑名单主键
     * @return 结果
     */
    @Override
    public int deleteBlacklistById(Long id)
    {
        return blacklistMapper.deleteBlacklistById(id);
    }
}
