package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RecurringScheduleEnum {

    // 周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义
    NOT_SET(1), EVERY_DAY(2), EVERY_WEEK(3), EVERY_MONTH(4), CUSTOM(5);

    private int type;
}
