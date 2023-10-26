package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class FollowUpTemplatesListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板内容
     */
    private String content;
}
