package com.ruoyi.customer.domain.bo;

import lombok.Data;

/**
 * 客群条件规则
 */
@Data
public class SegmentConditionRuleBO {

    /**
     * 字段ID
     */
    private Long columnId;

    /**
     * 逻辑与
     */
    private Integer andOr;

    /**
     * 条件运算符 1.等于 2.不等于 3.属于 4.不属于 5.为空 6.不为空 7.早于 8.晚于
     */
    private Integer conditionType;

    /**
     * 日期类型 1.具体日期范围 2.动态日期范围 3.当天之前 4.当天之后 5.具体日期 6.日期字段
     */
    private Integer dateType;

    /**
     * 值
     */
    private Object value;
}
