package com.ruoyi.customer.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.domain.vo.SegmentListVO;
import com.ruoyi.customer.domain.vo.SegmentUserListVO;
import com.ruoyi.customer.domain.vo.StageListVO;
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
    public int insertSegment(Segment segment);

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
     */
    List<SegmentListVO> list(@Param("createId") Long createId);

    /**
     * 统计客户数
     * @param focusFlag
     * @return
     */
    Integer countCustomerCount(@Param("focusFlag") Boolean focusFlag);

    /**
     * 用户列表
     * @return
     */
    List<SegmentUserListVO> userList();

    /**
     * 用户的客群客户数
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectSegmentCustomerCountByUserId(@Param("userId") Long userId);

    /**
     * 根据跟进人统计客户数
     * @param userId
     * @param focusFlag
     * @return
     */
    Integer countCustomerCountByUserId(@Param("userId") Long userId, @Param("focusFlag") Boolean focusFlag);
}
