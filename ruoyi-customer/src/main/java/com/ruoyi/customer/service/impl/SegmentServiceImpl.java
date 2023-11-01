package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SegmentMapper;
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;

/**
 * 客群Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@Service
public class SegmentServiceImpl implements ISegmentService 
{
    @Autowired
    private SegmentMapper segmentMapper;

    /**
     * 查询客群
     * 
     * @param id 客群主键
     * @return 客群
     */
    @Override
    public Segment selectSegmentById(Long id)
    {
        return segmentMapper.selectSegmentById(id);
    }

    /**
     * 查询客群列表
     * 
     * @param segment 客群
     * @return 客群
     */
    @Override
    public List<Segment> selectSegmentList(Segment segment)
    {
        return segmentMapper.selectSegmentList(segment);
    }

    /**
     * 新增客群
     * 
     * @param segment 客群
     * @return 结果
     */
    @Override
    public int insertSegment(Segment segment)
    {
        segment.setCreateTime(DateUtils.getNowDate());
        return segmentMapper.insertSegment(segment);
    }

    /**
     * 修改客群
     * 
     * @param segment 客群
     * @return 结果
     */
    @Override
    public int updateSegment(Segment segment)
    {
        segment.setUpdateTime(DateUtils.getNowDate());
        return segmentMapper.updateSegment(segment);
    }

    /**
     * 批量删除客群
     * 
     * @param ids 需要删除的客群主键
     * @return 结果
     */
    @Override
    public int deleteSegmentByIds(Long[] ids)
    {
        return segmentMapper.deleteSegmentByIds(ids);
    }

    /**
     * 删除客群信息
     * 
     * @param id 客群主键
     * @return 结果
     */
    @Override
    public int deleteSegmentById(Long id)
    {
        return segmentMapper.deleteSegmentById(id);
    }
}
