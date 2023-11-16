package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerPublicleadsRulesVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 客群名称
     */
    private String segmentNames;
}
