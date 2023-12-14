package com.ruoyi.customer.domain.bo;

import lombok.Data;

import java.util.Date;

@Data
public class EmailCountGroupByCustomerBO {

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 最近联系时间
     */
    private Date lastContactedAt;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 活跃度类型
     */
    private Integer activityType;

    /**
     * 活跃度名称
     */
    private String activityName;
}
