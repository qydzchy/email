package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TimeRangeEnum {
    TODAY(1, "今天"),
    YESTERDAY(2, "昨天"),
    TOMORROW(3, "明天"),
    THIS_WEEK(4, "本周"),
    LAST_WEEK(5, "上周"),
    NEXT_WEEK(6, "下周"),
    THIS_MONTH(7, "本月"),
    LAST_MONTH(8, "上月"),
    THIS_QUARTER(9, "本季度"),
    LAST_QUARTER(10, "上季度"),
    THIS_YEAR(11, "本年"),
    PAST_7_DAYS(12, "过去7天"),
    PAST_14_DAYS(13, "过去14天"),
    PAST_30_DAYS(14, "过去30天"),
    PAST_60_DAYS(15, "过去60天"),
    PAST_90_DAYS(16, "过去90天"),
    PAST_180_DAYS(17, "过去180天"),
    PAST_365_DAYS(18, "过去365天"),
    NEXT_3_DAYS(19, "未来3天"),
    NEXT_7_DAYS(20, "未来7天"),
    NEXT_14_DAYS(21, "未来14天"),
    NEXT_30_DAYS(22, "未来30天"),
    // 早于、晚于
    BEFORE_TODAY(23, "当天之前"),
    AFTER_TODAY(24, "当天之后"),
    SPECIFIC_DATE(25, "具体日期"),
    DATE_FIELD(26, "日期字段");

    private int code;
    private String description;

    public static TimeRangeEnum getByCode(Integer code) {
        if (code == null) return null;

        for (TimeRangeEnum timeRangeEnum : TimeRangeEnum.values()) {
            if (timeRangeEnum.getCode() == code) {
                return timeRangeEnum;
            }
        }
        return null;
    }
}
