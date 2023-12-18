package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class EmailGeneralListVO {

    private Long id;

    private String name;

    private Integer customerCount;

    /**
     * 未读邮件数量
     */
    private Integer unReadEmailCount;

    private List<EmailCustomerVO> customerList;
}
