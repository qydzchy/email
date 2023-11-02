package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.SegmentListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SegmentMapper;
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客群Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@Service
public class SegmentServiceImpl implements ISegmentService 
{
    @Resource
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
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Segment segment = new Segment();
        BeanUtils.copyProperties(segmentAddOrUpdateDTO, segment);
        segment.setCreateId(userId);
        segment.setCreateBy(username);
        segment.setCreateTime(DateUtils.getNowDate());
        segment.setUpdateId(userId);
        segment.setUpdateBy(username);
        segment.setUpdateTime(DateUtils.getNowDate());

        long id = segmentMapper.insertSegment(segment);
        List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getSubGroup();
        List<Segment> segmentList = new ArrayList<>();
        subGroupList.stream().forEach(subGroup -> {
            Segment subSegment = new Segment();
            subSegment.setParentId(id);
            BeanUtils.copyProperties(subGroup, subSegment);
            subSegment.setCreateId(userId);
            subSegment.setCreateBy(username);
            subSegment.setCreateTime(DateUtils.getNowDate());
            subSegment.setUpdateId(userId);
            subSegment.setUpdateBy(username);
            subSegment.setUpdateTime(DateUtils.getNowDate());
            segmentList.add(subSegment);
        });

        // 批量新增子客群
        segmentMapper.batchInsertSegment(segmentList);
        return true;
    }

    /**
     * 修改客群
     * 
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Segment segment = new Segment();
        BeanUtils.copyProperties(segmentAddOrUpdateDTO, segment);
        segment.setUpdateId(userId);
        segment.setUpdateBy(username);
        segment.setUpdateTime(DateUtils.getNowDate());
        segmentMapper.updateSegment(segment);

        // 删除子客群
        segmentMapper.deleteSegmentByParentId(segment.getId());

        List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getSubGroup();
        List<Segment> segmentList = new ArrayList<>();
        subGroupList.stream().forEach(subGroup -> {
            Segment subSegment = new Segment();
            subSegment.setParentId(segmentAddOrUpdateDTO.getId());
            BeanUtils.copyProperties(subGroup, subSegment);
            subSegment.setCreateId(userId);
            subSegment.setCreateBy(username);
            subSegment.setCreateTime(DateUtils.getNowDate());
            subSegment.setUpdateId(userId);
            subSegment.setUpdateBy(username);
            subSegment.setUpdateTime(DateUtils.getNowDate());
            segmentList.add(subSegment);
        });

        // 批量新增子客群
        segmentMapper.batchInsertSegment(segmentList);
        return true;
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return segmentMapper.deleteSegmentById(id, userId, username);
    }

    /**
     * 客群树
     * @param usageScope
     * @return
     */
    @Override
    public List<SegmentListVO> getSegmentTree(Integer usageScope) {
        List<SegmentListVO> segmentVOList = segmentMapper.list(usageScope);
        return buildTree(segmentVOList, -1L);
    }

    private List<SegmentListVO> buildTree(List<SegmentListVO> segmentVOList, Long parentId) {
        List<SegmentListVO> children = new ArrayList<>();

        for (SegmentListVO segmentVO : segmentVOList) {
            if ((parentId == null && segmentVO.getParentId() == null)
                    || (parentId != null && parentId.longValue() == segmentVO.getParentId().longValue())) {
                List<SegmentListVO> childSegments = buildTree(segmentVOList, segmentVO.getId());
                segmentVO.setChildren(childSegments);
                children.add(segmentVO);
            }
        }

        return children;
    }
}
