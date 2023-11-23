package com.ruoyi.customer.domain.dto;

import lombok.Data;

@Data
public class CustomerDocumentListDTO {

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 文档名称
     */
    private String name;

    /**
     * 文档类型
     */
    private String type;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
