package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.Blacklist;
import org.apache.ibatis.annotations.Param;

/**
 * 黑名单Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface BlacklistMapper 
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
     * 删除黑名单
     * 
     * @param id 黑名单主键
     * @return 结果
     */
    public int deleteBlacklistById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlacklistByIds(Long[] ids);

    /**
     * 根据类型和内容查询黑名单数量
     * @param type
     * @param content
     * @return
     */
    int countByTypeAndContent(@Param("type") Integer type, @Param("content") String content);
}
