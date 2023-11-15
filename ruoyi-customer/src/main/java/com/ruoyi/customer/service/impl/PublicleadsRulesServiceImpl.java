package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.customer.domain.PublicleadsRulesSegment;
import com.ruoyi.customer.domain.dto.PublicleadsRulesAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.PublicleadsRulesListVO;
import com.ruoyi.customer.mapper.PublicleadsRulesSegmentMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsRulesMapper;
import com.ruoyi.customer.domain.PublicleadsRules;
import com.ruoyi.customer.service.IPublicleadsRulesService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 移入公海规则Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsRulesServiceImpl implements IPublicleadsRulesService 
{
    @Resource
    private PublicleadsRulesMapper publicleadsRulesMapper;
    @Resource
    private PublicleadsRulesSegmentMapper publicleadsRulesSegmentMapper;

    /**
     * 查询移入公海规则列表
     * 
     * @param publicleadsRules 移入公海规则
     * @return 移入公海规则
     */
    @Override
    public List<PublicleadsRules> selectPublicleadsRulesList(PublicleadsRules publicleadsRules)
    {
        return publicleadsRulesMapper.selectPublicleadsRulesList(publicleadsRules);
    }

    /**
     * 新增移入公海规则
     * 
     * @param publicleadsRulesAddOrUpdateDTO 移入公海规则
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertPublicleadsRules(PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        PublicleadsRules publicleadsRules = new PublicleadsRules();
        BeanUtils.copyProperties(publicleadsRulesAddOrUpdateDTO, publicleadsRules);
        publicleadsRules.setStatus(0);
        publicleadsRules.setCreateId(userId);
        publicleadsRules.setCreateBy(username);
        publicleadsRules.setCreateTime(DateUtils.getNowDate());
        publicleadsRules.setUpdateId(userId);
        publicleadsRules.setUpdateBy(username);
        publicleadsRules.setUpdateTime(DateUtils.getNowDate());
        publicleadsRulesMapper.insertPublicleadsRules(publicleadsRules);

        List<Long> segmentIdList = publicleadsRulesAddOrUpdateDTO.getSegmentIdList();
        List<PublicleadsRulesSegment> publicleadsRulesSegmentList = new ArrayList<>();
        for (Long segmentId : segmentIdList) {
            PublicleadsRulesSegment publicleadsRulesSegment = new PublicleadsRulesSegment();
            publicleadsRulesSegment.setPublicleadsRulesId(publicleadsRules.getId());
            publicleadsRulesSegment.setSegmentId(segmentId);
            publicleadsRulesSegmentList.add(publicleadsRulesSegment);
        }

        // 批量新增公海规则客群
        publicleadsRulesSegmentMapper.batchInsertPublicleadsRulesSegment(publicleadsRulesSegmentList);
        return true;
    }

    /**
     * 修改移入公海规则
     * 
     * @param publicleadsRulesAddOrUpdateDTO 移入公海规则
     * @return 结果
     */
    @Override
    public boolean updatePublicleadsRules(PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Long id = publicleadsRulesAddOrUpdateDTO.getId();
        PublicleadsRules publicleadsRules = publicleadsRulesMapper.selectPublicleadsRulesById(id);
        BeanUtils.copyProperties(publicleadsRulesAddOrUpdateDTO, publicleadsRules);
        publicleadsRules.setUpdateId(userId);
        publicleadsRules.setUpdateBy(username);
        publicleadsRules.setUpdateTime(DateUtils.getNowDate());
        publicleadsRulesMapper.updatePublicleadsRules(publicleadsRules);

        // 删除公海规则客群
        publicleadsRulesSegmentMapper.deleteByPublicleadsRulesId(id);

        // 批量新增公海规则客群
        List<Long> segmentIdList = publicleadsRulesAddOrUpdateDTO.getSegmentIdList();
        List<PublicleadsRulesSegment> publicleadsRulesSegmentList = new ArrayList<>();
        for (Long segmentId : segmentIdList) {
            PublicleadsRulesSegment publicleadsRulesSegment = new PublicleadsRulesSegment();
            publicleadsRulesSegment.setPublicleadsRulesId(publicleadsRules.getId());
            publicleadsRulesSegment.setSegmentId(segmentId);
            publicleadsRulesSegmentList.add(publicleadsRulesSegment);
        }
        publicleadsRulesSegmentMapper.batchInsertPublicleadsRulesSegment(publicleadsRulesSegmentList);
        return true;
    }

    /**
     * 删除移入公海规则信息
     * 
     * @param id 移入公海规则主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsRulesById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return publicleadsRulesMapper.deletePublicleadsRulesById(id, userId, username);
    }

    @Override
    public List<PublicleadsRulesListVO> list() {
        return publicleadsRulesMapper.list();
    }
}
