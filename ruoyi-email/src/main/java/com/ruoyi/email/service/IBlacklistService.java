package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Blacklist;

/**
 * 黑名单Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface IBlacklistService 
{
    /**
     * 查询黑名单
     * 
     * @param id 黑名单主键
     * @return 黑名单
     */
    public Blacklist selectBlacklistById(Long id);

    /**
     * 查询黑名单列表
     * 
     * @param blacklist 黑名单
     * @return 黑名单集合
     */
    public List<Blacklist> selectBlacklistList(Blacklist blacklist);

    /**
     * 新增黑名单
     * 
     * @param blacklist 黑名单
     * @return 结果
     */
    public int insertBlacklist(Blacklist blacklist);

    /**
     * 修改黑名单
     * 
     * @param blacklist 黑名单
     * @return 结果
     */
    public int updateBlacklist(Blacklist blacklist);

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的黑名单主键集合
     * @return 结果
     */
    public int deleteBlacklistByIds(Long[] ids);

    /**
     * 删除黑名单信息
     * 
     * @param id 黑名单主键
     * @return 结果
     */
    public int deleteBlacklistById(Long id);
}
