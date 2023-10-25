package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsReasonMapper;
import com.ruoyi.customer.domain.PublicleadsReason;
import com.ruoyi.customer.service.IPublicleadsReasonService;

/**
 * 移入公海原因Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsReasonServiceImpl implements IPublicleadsReasonService 
{
    @Autowired
    private PublicleadsReasonMapper publicleadsReasonMapper;

    /**
     * 查询移入公海原因
     * 
     * @param id 移入公海原因主键
     * @return 移入公海原因
     */
    @Override
    public PublicleadsReason selectPublicleadsReasonById(Long id)
    {
        return publicleadsReasonMapper.selectPublicleadsReasonById(id);
    }

    /**
     * 查询移入公海原因列表
     * 
     * @param publicleadsReason 移入公海原因
     * @return 移入公海原因
     */
    @Override
    public List<PublicleadsReason> selectPublicleadsReasonList(PublicleadsReason publicleadsReason)
    {
        return publicleadsReasonMapper.selectPublicleadsReasonList(publicleadsReason);
    }

    /**
     * 新增移入公海原因
     * 
     * @param publicleadsReason 移入公海原因
     * @return 结果
     */
    @Override
    public int insertPublicleadsReason(PublicleadsReason publicleadsReason)
    {
        publicleadsReason.setCreateTime(DateUtils.getNowDate());
        return publicleadsReasonMapper.insertPublicleadsReason(publicleadsReason);
    }

    /**
     * 修改移入公海原因
     * 
     * @param publicleadsReason 移入公海原因
     * @return 结果
     */
    @Override
    public int updatePublicleadsReason(PublicleadsReason publicleadsReason)
    {
        publicleadsReason.setUpdateTime(DateUtils.getNowDate());
        return publicleadsReasonMapper.updatePublicleadsReason(publicleadsReason);
    }

    /**
     * 批量删除移入公海原因
     * 
     * @param ids 需要删除的移入公海原因主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsReasonByIds(Long[] ids)
    {
        return publicleadsReasonMapper.deletePublicleadsReasonByIds(ids);
    }

    /**
     * 删除移入公海原因信息
     * 
     * @param id 移入公海原因主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsReasonById(Long id)
    {
        return publicleadsReasonMapper.deletePublicleadsReasonById(id);
    }
}
