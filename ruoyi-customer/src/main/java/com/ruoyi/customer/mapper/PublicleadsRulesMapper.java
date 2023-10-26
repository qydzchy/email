package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsRules;
import org.apache.ibatis.annotations.Param;

/**
 * 移入公海规则Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface PublicleadsRulesMapper 
{
    /**
     * 查询移入公海规则
     * 
     * @param id 移入公海规则主键
     * @return 移入公海规则
     */
    public PublicleadsRules selectPublicleadsRulesById(Long id);

    /**
     * 查询移入公海规则列表
     * 
     * @param publicleadsRules 移入公海规则
     * @return 移入公海规则集合
     */
    public List<PublicleadsRules> selectPublicleadsRulesList(PublicleadsRules publicleadsRules);

    /**
     * 新增移入公海规则
     * 
     * @param publicleadsRules 移入公海规则
     * @return 结果
     */
    public int insertPublicleadsRules(PublicleadsRules publicleadsRules);

    /**
     * 修改移入公海规则
     * 
     * @param publicleadsRules 移入公海规则
     * @return 结果
     */
    public int updatePublicleadsRules(PublicleadsRules publicleadsRules);

    /**
     * 删除移入公海规则
     * 
     * @param id 移入公海规则主键
     * @return 结果
     */
    public int deletePublicleadsRulesById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除移入公海规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsRulesByIds(Long[] ids);
}
