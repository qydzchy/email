package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsReason;

/**
 * 移入公海原因Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsReasonService 
{
    /**
     * 查询移入公海原因
     * 
     * @param id 移入公海原因主键
     * @return 移入公海原因
     */
    public PublicleadsReason selectPublicleadsReasonById(Long id);

    /**
     * 查询移入公海原因列表
     * 
     * @param publicleadsReason 移入公海原因
     * @return 移入公海原因集合
     */
    public List<PublicleadsReason> selectPublicleadsReasonList(PublicleadsReason publicleadsReason);

    /**
     * 新增移入公海原因
     * 
     * @param publicleadsReason 移入公海原因
     * @return 结果
     */
    public int insertPublicleadsReason(PublicleadsReason publicleadsReason);

    /**
     * 修改移入公海原因
     * 
     * @param publicleadsReason 移入公海原因
     * @return 结果
     */
    public int updatePublicleadsReason(PublicleadsReason publicleadsReason);

    /**
     * 批量删除移入公海原因
     * 
     * @param ids 需要删除的移入公海原因主键集合
     * @return 结果
     */
    public int deletePublicleadsReasonByIds(Long[] ids);

    /**
     * 删除移入公海原因信息
     * 
     * @param id 移入公海原因主键
     * @return 结果
     */
    public int deletePublicleadsReasonById(Long id);
}
