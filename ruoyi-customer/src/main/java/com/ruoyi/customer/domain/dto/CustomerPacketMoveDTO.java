package com.ruoyi.customer.domain.dto;

import lombok.Data;

@Data
public class CustomerPacketMoveDTO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 分组ID
     */
    private Long packetId;
}
