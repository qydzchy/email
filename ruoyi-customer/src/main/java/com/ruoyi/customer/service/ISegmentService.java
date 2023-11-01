package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Segment;

/**
 * 客群Service接口
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
public interface ISegmentService 
{
    /**
     * 查询客群
     * 
     * @param id 客群主键
     * @return 客群
     */
    public Segment selectSegmentById(Long id);

    /**
     * 查询客群列表
     * 
     * @param segment 客群
     * @return 客群集合
     */
    public List<Segment> selectSegmentList(Segment segment);

    /**
     * 新增客群
     * 
     * @param segment 客群
     * @return 结果
     */
    public int insertSegment(Segment segment);

    /**
     * 修改客群
     * 
     * @param segment 客群
     * @return 结果
     */
    public int updateSegment(Segment segment);

    /**
     * 批量删除客群
     * 
     * @param ids 需要删除的客群主键集合
     * @return 结果
     */
    public int deleteSegmentByIds(Long[] ids);

    /**
     * 删除客群信息
     * 
     * @param id 客群主键
     * @return 结果
     */
    public int deleteSegmentById(Long id);
}
