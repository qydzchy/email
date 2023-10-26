package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class StageListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 阶段名称
     */
    private String name;

    /**
     * 阶段颜色
     */
    private String color;
}
