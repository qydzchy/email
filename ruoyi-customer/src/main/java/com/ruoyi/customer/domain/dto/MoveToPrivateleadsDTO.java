package com.ruoyi.customer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class MoveToPrivateleadsDTO {

    /**
     * 客户ID
     */
    private List<Long> id;

    /**
     * 分组ID
     */
    private Long packetId;
}
