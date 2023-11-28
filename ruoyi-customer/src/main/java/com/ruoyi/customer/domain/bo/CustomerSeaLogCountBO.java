package com.ruoyi.customer.domain.bo;

import lombok.Data;

@Data
public class CustomerSeaLogCountBO {

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 移入次数
     */
    private Integer moveCount;
}
