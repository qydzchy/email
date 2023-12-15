package com.ruoyi.customer.domain.bo;

import lombok.Data;

@Data
public class EmailCountGroupBySourceBO {
    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 客户来源
     */
    private Long sourceId;
}
