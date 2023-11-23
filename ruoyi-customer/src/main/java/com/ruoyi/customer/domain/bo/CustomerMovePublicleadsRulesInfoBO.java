package com.ruoyi.customer.domain.bo;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerMovePublicleadsRulesInfoBO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 最近联系时间
     */
    private Date lastContactedAt;

    /**
     * 跟进人ID
     */
    private Long userId;
}
