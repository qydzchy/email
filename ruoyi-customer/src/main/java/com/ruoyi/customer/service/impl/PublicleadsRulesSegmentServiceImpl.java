package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsRulesSegmentMapper;
import com.ruoyi.customer.domain.PublicleadsRulesSegment;
import com.ruoyi.customer.service.IPublicleadsRulesSegmentService;

/**
 * 公海规则客群Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
@Service
public class PublicleadsRulesSegmentServiceImpl implements IPublicleadsRulesSegmentService 
{
    @Autowired
    private PublicleadsRulesSegmentMapper publicleadsRulesSegmentMapper;

    /**
     * 查询公海规则客群
     * 
     * @param id 公海规则客群主键
     * @return 公海规则客群
     */
    @Override
    public PublicleadsRulesSegment selectPublicleadsRulesSegmentById(Long id)
    {
        return publicleadsRulesSegmentMapper.selectPublicleadsRulesSegmentById(id);
    }

    /**
     * 查询公海规则客群列表
     * 
     * @param publicleadsRulesSegment 公海规则客群
     * @return 公海规则客群
     */
    @Override
    public List<PublicleadsRulesSegment> selectPublicleadsRulesSegmentList(PublicleadsRulesSegment publicleadsRulesSegment)
    {
        return publicleadsRulesSegmentMapper.selectPublicleadsRulesSegmentList(publicleadsRulesSegment);
    }

    /**
     * 新增公海规则客群
     * 
     * @param publicleadsRulesSegment 公海规则客群
     * @return 结果
     */
    @Override
    public int insertPublicleadsRulesSegment(PublicleadsRulesSegment publicleadsRulesSegment)
    {
        return publicleadsRulesSegmentMapper.insertPublicleadsRulesSegment(publicleadsRulesSegment);
    }

    /**
     * 修改公海规则客群
     * 
     * @param publicleadsRulesSegment 公海规则客群
     * @return 结果
     */
    @Override
    public int updatePublicleadsRulesSegment(PublicleadsRulesSegment publicleadsRulesSegment)
    {
        return publicleadsRulesSegmentMapper.updatePublicleadsRulesSegment(publicleadsRulesSegment);
    }

    /**
     * 批量删除公海规则客群
     * 
     * @param ids 需要删除的公海规则客群主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsRulesSegmentByIds(Long[] ids)
    {
        return publicleadsRulesSegmentMapper.deletePublicleadsRulesSegmentByIds(ids);
    }

    /**
     * 删除公海规则客群信息
     * 
     * @param id 公海规则客群主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsRulesSegmentById(Long id)
    {
        return publicleadsRulesSegmentMapper.deletePublicleadsRulesSegmentById(id);
    }
}
