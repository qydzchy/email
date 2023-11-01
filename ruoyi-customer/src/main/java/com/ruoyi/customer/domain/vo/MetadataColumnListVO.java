package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class MetadataColumnListVO {

    /**
     * 字段ID
     */
    private Long id;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段别名
     */
    private String columnAlias;

    /**
     * 字段类型
     */
    private String columnType;
}
