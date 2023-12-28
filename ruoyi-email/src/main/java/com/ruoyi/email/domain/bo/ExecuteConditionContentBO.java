package com.ruoyi.email.domain.bo;

import lombok.Data;

@Data
public class ExecuteConditionContentBO {

    /**
     * 逻辑与
     */
    private String andOr;

    /**
     * 条件运算符
     */
    private Integer conditionType;

    /**
     * 值
     */
    private String value;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 客户分组ID
     */
    private Long packetId;
}
