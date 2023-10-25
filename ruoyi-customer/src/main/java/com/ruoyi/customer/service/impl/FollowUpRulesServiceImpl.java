package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FollowUpRulesMapper;
import com.ruoyi.customer.domain.FollowUpRules;
import com.ruoyi.customer.service.IFollowUpRulesService;

/**
 * 客户跟进规则Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class FollowUpRulesServiceImpl implements IFollowUpRulesService 
{
    @Autowired
    private FollowUpRulesMapper followUpRulesMapper;

    /**
     * 查询客户跟进规则
     * 
     * @param id 客户跟进规则主键
     * @return 客户跟进规则
     */
    @Override
    public FollowUpRules selectFollowUpRulesById(Long id)
    {
        return followUpRulesMapper.selectFollowUpRulesById(id);
    }

    /**
     * 查询客户跟进规则列表
     * 
     * @param followUpRules 客户跟进规则
     * @return 客户跟进规则
     */
    @Override
    public List<FollowUpRules> selectFollowUpRulesList(FollowUpRules followUpRules)
    {
        return followUpRulesMapper.selectFollowUpRulesList(followUpRules);
    }

    /**
     * 新增客户跟进规则
     * 
     * @param followUpRules 客户跟进规则
     * @return 结果
     */
    @Override
    public int insertFollowUpRules(FollowUpRules followUpRules)
    {
        followUpRules.setCreateTime(DateUtils.getNowDate());
        return followUpRulesMapper.insertFollowUpRules(followUpRules);
    }

    /**
     * 修改客户跟进规则
     * 
     * @param followUpRules 客户跟进规则
     * @return 结果
     */
    @Override
    public int updateFollowUpRules(FollowUpRules followUpRules)
    {
        followUpRules.setUpdateTime(DateUtils.getNowDate());
        return followUpRulesMapper.updateFollowUpRules(followUpRules);
    }

    /**
     * 批量删除客户跟进规则
     * 
     * @param ids 需要删除的客户跟进规则主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpRulesByIds(Long[] ids)
    {
        return followUpRulesMapper.deleteFollowUpRulesByIds(ids);
    }

    /**
     * 删除客户跟进规则信息
     * 
     * @param id 客户跟进规则主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpRulesById(Long id)
    {
        return followUpRulesMapper.deleteFollowUpRulesById(id);
    }
}
