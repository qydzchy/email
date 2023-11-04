package com.ruoyi.customer.domain.dto;

import lombok.Data;

@Data
public class UnassignFollowUpDTO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 跟进人
     */
    private Long userId;
}
