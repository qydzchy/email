package com.ruoyi.email.domain.vo.label;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabelListVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 标签类型 1.系统标签 2.自定义标签
     */
    private Integer type;
}
