package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class BlackListRecordsListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 邮箱/邮箱后缀
     */
    private String domain;
}
