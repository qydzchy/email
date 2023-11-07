package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DateTimeEnum {

    CREATE_TIME("create_time", "创建时间"),
    DATA_UPDATE_TIME("data_update_time", "资料更新时间"),
    LAST_CONTACTED_AT("last_contacted_at", "最近联系时间"),
    LAST_FOLLOWUP_AT("last_followup_at", "最近跟进时间"),
    NEXT_SCHEDULE_TIME("next_schedule_time", "下次日程时间");

    private String columnName;
    private String nickName;
}
