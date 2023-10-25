package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsRulesMapper;
import com.ruoyi.customer.domain.PublicleadsRules;
import com.ruoyi.customer.service.IPublicleadsRulesService;

/**
 * 移入公海规则Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsRulesServiceImpl implements IPublicleadsRulesService 
{
    @Autowired
    private PublicleadsRulesMapper publicleadsRulesMapper;

    /**
     * 查询移入公海规则
     * 
     * @param id 移入公海规则主键
     * @return 移入公海规则
     */
    @Override
    public PublicleadsRules selectPublicleadsRulesById(Long id)
    {
        return publicleadsRulesMapper.selectPublicleadsRulesById(id);
    }

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
     * @param publicleadsRules 移入公海规则
     * @return 结果
     */
    @Override
    public int insertPublicleadsRules(PublicleadsRules publicleadsRules)
    {
        publicleadsRules.setCreateTime(DateUtils.getNowDate());
        return publicleadsRulesMapper.insertPublicleadsRules(publicleadsRules);
    }

    /**
     * 修改移入公海规则
     * 
     * @param publicleadsRules 移入公海规则
     * @return 结果
     */
    @Override
    public int updatePublicleadsRules(PublicleadsRules publicleadsRules)
    {
        publicleadsRules.setUpdateTime(DateUtils.getNowDate());
        return publicleadsRulesMapper.updatePublicleadsRules(publicleadsRules);
    }

    /**
     * 批量删除移入公海规则
     * 
     * @param ids 需要删除的移入公海规则主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsRulesByIds(Long[] ids)
    {
        return publicleadsRulesMapper.deletePublicleadsRulesByIds(ids);
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
        return publicleadsRulesMapper.deletePublicleadsRulesById(id);
    }
}
