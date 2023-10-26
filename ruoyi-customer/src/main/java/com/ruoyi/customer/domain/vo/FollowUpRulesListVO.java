package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class FollowUpRulesListVO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /** 类型
     1. 发生以下行为会影响客户的“最近联系时间”，进而影响客户被自动移入公海的时间
     2. 发生以下行为会影响客户的“最近跟进时间”，进而影响跟进客户类型的任务截止时间 */
    private Integer type;

    /**
     * 标志 0.未选 1.选中
     */
    private Boolean activeFlag;
}
