package com.ruoyi.customer.domain.dto;

import lombok.Data;

@Data
public class CustomerFollowUpRecordsListDTO {

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 搜索条件
     */
    private String searchText;
}
