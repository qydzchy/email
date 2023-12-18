package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class EmailCustomerVO {

    private Long id;

    private String name;

    private Integer customerCount;

    /**
     * 未读邮件数量
     */
    private Integer unReadEmailCount;
}
