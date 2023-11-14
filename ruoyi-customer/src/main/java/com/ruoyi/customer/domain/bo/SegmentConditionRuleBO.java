package com.ruoyi.customer.domain.bo;

import lombok.Data;

/**
 * 客群条件规则
 */
@Data
public class SegmentConditionRuleBO {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 逻辑与
     */
    private String andOr;

    /**
     * 条件运算符 1.等于 2.不等于 3.属于 4.不属于 5.为空 6.不为空 7.早于 8.晚于
     */
    private Integer conditionType;

    /**
     * 日期类型 1.具体日期范围 2.动态日期范围 3.当天之前 4.当天之后 5.具体日期 6.日期字段
     */
    private Integer dateType;

    /**
     * 时间范围 1. 今天 2. 昨天 3.明天 4.本周 5.上周 6.下周 7.本月 8.上月 9.本季度 10.上季度 11.本年 12.过去7天 13.过去14天 14.过去30天 15.过去60天 15.过去90天 15.过去180天 16.过去365天 17.未来3天 17.未来7天 18.未来14天 19.未来30天
     */
    private Integer timeRange;

    /**
     * 值
     */
    private Object value;
}
