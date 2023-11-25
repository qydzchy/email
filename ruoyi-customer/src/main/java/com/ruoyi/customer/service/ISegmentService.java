package com.ruoyi.customer.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.*;

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
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    public boolean insertSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO);

    /**
     * 修改客群
     * 
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    public boolean updateSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO);

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
    public boolean deleteSegmentById(Long id);

    /**
     * 查询客群树列表
     * @return
     */
    List<SegmentListVO> getSegmentTree(Long createId);

    /**
     * 获取字段
     * @return
     */
    List<Map<String, Object>> getConditionRuleColumn();

    /**
     * 用户列表
     * @return
     */
    List<SegmentUserListVO> userList();

    /**
     * 客户列表-客群列表
     * @return
     */
    List<CustomerSegmentListVO> segmentList();

    /**
     * 可见范围是否成立
     * @param userDeptInfoBOList
     * @param segment
     * @return
     */
    boolean isVisibleConditionMet(List<UserDeptInfoBO> userDeptInfoBOList, Segment segment);

    /**
     * 客群列表（下拉）
     * @return
     */
    List<SegmentListVO> simpleList();

    /**
     * 二级客群字段
     * @return
     */
    List<SubgroupColumnVO> subgroupColumn();

    /**
     * 二级客群字段列表
     * @return
     */
    List<SubgroupColumnListVO> subgroupColumnList(String columnName);

    /**
     * 客群详情
     * @param id
     * @return
     */
    SegmentListVO detail(Long id);
}
