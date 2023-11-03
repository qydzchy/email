package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.domain.vo.SegmentListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 客群Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
public interface SegmentMapper 
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
    public long insertSegment(Segment segment);

    /**
     * 修改客群
     * 
     * @param segment 客群
     * @return 结果
     */
    public int updateSegment(Segment segment);

    /**
     * 删除客群
     * 
     * @param id 客群主键
     * @return 结果
     */
    public int deleteSegmentById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客群
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSegmentByIds(Long[] ids);

    /**
     * 批量新增客群
     * @param segmentList
     */
    void batchInsertSegment(@Param("segmentList") List<Segment> segmentList);

    /**
     * 删除子客群
     * @param parentId
     */
    void deleteSegmentByParentId(@Param("parentId") Long parentId);

    /**
     * 查询客群列表
     * @param usageScope
     */
    List<SegmentListVO> list(@Param("usageScope") Integer usageScope);
}