package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsRules;
import com.ruoyi.customer.domain.dto.PublicleadsRulesAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.CustomerPublicleadsRulesSettingsVO;
import com.ruoyi.customer.domain.vo.PublicleadsRulesListVO;

/**
 * 移入公海规则Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsRulesService 
{
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
     * @param publicleadsRulesAddOrUpdateDTO 移入公海规则
     * @return 结果
     */
    public boolean insertPublicleadsRules(PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO);

    /**
     * 修改移入公海规则
     * 
     * @param publicleadsRulesAddOrUpdateDTO 移入公海规则
     * @return 结果
     */
    public boolean updatePublicleadsRules(PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO);

    /**
     * 删除移入公海规则信息
     * 
     * @param id 移入公海规则主键
     * @return 结果
     */
    public int deletePublicleadsRulesById(Long id);

    /**
     * 移入公海规则列表
     * @return
     */
    List<PublicleadsRulesListVO> list();

    /**
     * 客户设置-移入公海规则列表
     * @return
     */
    CustomerPublicleadsRulesSettingsVO publicleadsRulesList();
}
