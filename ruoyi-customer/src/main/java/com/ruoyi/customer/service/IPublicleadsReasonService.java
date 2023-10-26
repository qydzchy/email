package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsReason;
import com.ruoyi.customer.domain.vo.PublicleadsReasonListVO;

/**
 * 移入公海原因Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsReasonService 
{
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
     * 删除移入公海原因信息
     * 
     * @param id 移入公海原因主键
     * @return 结果
     */
    public int deletePublicleadsReasonById(Long id);

    /**
     * 移入公海原因列表
     * @return
     */
    List<PublicleadsReasonListVO> list();
}
