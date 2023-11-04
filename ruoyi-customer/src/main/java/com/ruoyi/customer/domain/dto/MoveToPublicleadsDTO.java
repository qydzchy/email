package com.ruoyi.customer.domain.dto;

import lombok.Data;

@Data
public class MoveToPublicleadsDTO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 公海分组ID
     */
    private Long publicleadsGroupsId;

    /**
     * 移入公海原因ID
     */
    private Long publicleadsReasonId;
}
