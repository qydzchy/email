package com.ruoyi.customer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerPacketMoveDTO {

    /**
     * 客户ID
     */
    private List<Long> ids;

    /**
     * 分组ID
     */
    private Long packetId;
}
