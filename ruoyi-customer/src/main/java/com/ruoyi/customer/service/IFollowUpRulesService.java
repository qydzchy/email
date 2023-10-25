package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.FollowUpRules;

/**
 * 客户跟进规则Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IFollowUpRulesService 
{
    /**
     * 查询客户跟进规则
     * 
     * @param id 客户跟进规则主键
     * @return 客户跟进规则
     */
    public FollowUpRules selectFollowUpRulesById(Long id);

    /**
     * 查询客户跟进规则列表
     * 
     * @param followUpRules 客户跟进规则
     * @return 客户跟进规则集合
     */
    public List<FollowUpRules> selectFollowUpRulesList(FollowUpRules followUpRules);

    /**
     * 新增客户跟进规则
     * 
     * @param followUpRules 客户跟进规则
     * @return 结果
     */
    public int insertFollowUpRules(FollowUpRules followUpRules);

    /**
     * 修改客户跟进规则
     * 
     * @param followUpRules 客户跟进规则
     * @return 结果
     */
    public int updateFollowUpRules(FollowUpRules followUpRules);

    /**
     * 批量删除客户跟进规则
     * 
     * @param ids 需要删除的客户跟进规则主键集合
     * @return 结果
     */
    public int deleteFollowUpRulesByIds(Long[] ids);

    /**
     * 删除客户跟进规则信息
     * 
     * @param id 客户跟进规则主键
     * @return 结果
     */
    public int deleteFollowUpRulesById(Long id);
}
