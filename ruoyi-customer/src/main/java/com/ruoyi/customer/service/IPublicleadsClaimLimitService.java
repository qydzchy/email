package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsClaimLimit;

/**
 * 领取上限Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsClaimLimitService 
{
    /**
     * 查询领取上限
     * 
     * @param id 领取上限主键
     * @return 领取上限
     */
    public PublicleadsClaimLimit selectPublicleadsClaimLimitById(Long id);

    /**
     * 查询领取上限列表
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 领取上限集合
     */
    public List<PublicleadsClaimLimit> selectPublicleadsClaimLimitList(PublicleadsClaimLimit publicleadsClaimLimit);

    /**
     * 新增领取上限
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 结果
     */
    public int insertPublicleadsClaimLimit(PublicleadsClaimLimit publicleadsClaimLimit);

    /**
     * 修改领取上限
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 结果
     */
    public int updatePublicleadsClaimLimit(PublicleadsClaimLimit publicleadsClaimLimit);

    /**
     * 批量删除领取上限
     * 
     * @param ids 需要删除的领取上限主键集合
     * @return 结果
     */
    public int deletePublicleadsClaimLimitByIds(Long[] ids);

    /**
     * 删除领取上限信息
     * 
     * @param id 领取上限主键
     * @return 结果
     */
    public int deletePublicleadsClaimLimitById(Long id);
}
