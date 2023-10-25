package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsClaimLimitMapper;
import com.ruoyi.customer.domain.PublicleadsClaimLimit;
import com.ruoyi.customer.service.IPublicleadsClaimLimitService;

/**
 * 领取上限Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsClaimLimitServiceImpl implements IPublicleadsClaimLimitService 
{
    @Autowired
    private PublicleadsClaimLimitMapper publicleadsClaimLimitMapper;

    /**
     * 查询领取上限
     * 
     * @param id 领取上限主键
     * @return 领取上限
     */
    @Override
    public PublicleadsClaimLimit selectPublicleadsClaimLimitById(Long id)
    {
        return publicleadsClaimLimitMapper.selectPublicleadsClaimLimitById(id);
    }

    /**
     * 查询领取上限列表
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 领取上限
     */
    @Override
    public List<PublicleadsClaimLimit> selectPublicleadsClaimLimitList(PublicleadsClaimLimit publicleadsClaimLimit)
    {
        return publicleadsClaimLimitMapper.selectPublicleadsClaimLimitList(publicleadsClaimLimit);
    }

    /**
     * 新增领取上限
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 结果
     */
    @Override
    public int insertPublicleadsClaimLimit(PublicleadsClaimLimit publicleadsClaimLimit)
    {
        publicleadsClaimLimit.setCreateTime(DateUtils.getNowDate());
        return publicleadsClaimLimitMapper.insertPublicleadsClaimLimit(publicleadsClaimLimit);
    }

    /**
     * 修改领取上限
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 结果
     */
    @Override
    public int updatePublicleadsClaimLimit(PublicleadsClaimLimit publicleadsClaimLimit)
    {
        publicleadsClaimLimit.setUpdateTime(DateUtils.getNowDate());
        return publicleadsClaimLimitMapper.updatePublicleadsClaimLimit(publicleadsClaimLimit);
    }

    /**
     * 批量删除领取上限
     * 
     * @param ids 需要删除的领取上限主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsClaimLimitByIds(Long[] ids)
    {
        return publicleadsClaimLimitMapper.deletePublicleadsClaimLimitByIds(ids);
    }

    /**
     * 删除领取上限信息
     * 
     * @param id 领取上限主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsClaimLimitById(Long id)
    {
        return publicleadsClaimLimitMapper.deletePublicleadsClaimLimitById(id);
    }
}
