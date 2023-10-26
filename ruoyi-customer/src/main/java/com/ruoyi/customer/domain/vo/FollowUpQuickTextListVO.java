package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class FollowUpQuickTextListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签（以分号隔开）
     */
    private String label;
}
