package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class EmailSourceVOList {

    private Long id;

    private String name;

    private Integer customerCount;

    private Integer emailCount;
}