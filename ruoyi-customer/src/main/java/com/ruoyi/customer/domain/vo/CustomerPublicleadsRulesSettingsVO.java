package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class CustomerPublicleadsRulesSettingsVO {

    /**
     * 客户数
     */
    private Integer customerCount;

    /** 类型 1.不限 2.客户上限 */
    private Integer type;

    /** 客户上限值 */
    private Integer limits;

    /**
     * 移入公海规则
     */
    private List<CustomerPublicleadsRulesVO> customerPublicleadsRulesList;
}
