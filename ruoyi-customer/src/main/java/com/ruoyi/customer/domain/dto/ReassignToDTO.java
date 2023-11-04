package com.ruoyi.customer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReassignToDTO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 跟进人ID
     */
    private List<Long> userIds;
}
