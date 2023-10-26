package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsReason;
import org.apache.ibatis.annotations.Param;

/**
 * 移入公海原因Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface PublicleadsReasonMapper 
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
     * 删除移入公海原因
     * 
     * @param id 移入公海原因主键
     * @return 结果
     */
    public int deletePublicleadsReasonById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除移入公海原因
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsReasonByIds(Long[] ids);
}
