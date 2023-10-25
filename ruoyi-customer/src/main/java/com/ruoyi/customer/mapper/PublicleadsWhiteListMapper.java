package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsWhiteList;

/**
 * 移入公海白名单Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface PublicleadsWhiteListMapper 
{
    /**
     * 查询移入公海白名单
     * 
     * @param id 移入公海白名单主键
     * @return 移入公海白名单
     */
    public PublicleadsWhiteList selectPublicleadsWhiteListById(Long id);

    /**
     * 查询移入公海白名单列表
     * 
     * @param publicleadsWhiteList 移入公海白名单
     * @return 移入公海白名单集合
     */
    public List<PublicleadsWhiteList> selectPublicleadsWhiteListList(PublicleadsWhiteList publicleadsWhiteList);

    /**
     * 新增移入公海白名单
     * 
     * @param publicleadsWhiteList 移入公海白名单
     * @return 结果
     */
    public int insertPublicleadsWhiteList(PublicleadsWhiteList publicleadsWhiteList);

    /**
     * 修改移入公海白名单
     * 
     * @param publicleadsWhiteList 移入公海白名单
     * @return 结果
     */
    public int updatePublicleadsWhiteList(PublicleadsWhiteList publicleadsWhiteList);

    /**
     * 删除移入公海白名单
     * 
     * @param id 移入公海白名单主键
     * @return 结果
     */
    public int deletePublicleadsWhiteListById(Long id);

    /**
     * 批量删除移入公海白名单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsWhiteListByIds(Long[] ids);
}
