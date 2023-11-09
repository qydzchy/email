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
    NEXT_SCHEDULE_TIME("next_schedule_time", "下次日程时间"),
    LAST_PRIVATELEADS_ENTRY("last_privateleads_entry", "最近进入私海时间"),
    LAST_PUBLICLEADS_ENTRY("last_publicleads_entry", "最近进入公海时间"),
    NEXT_PUBLICLEADS_DATE("next_publicleads_date", "下次移入公海日期"),
    LAST_FOLLOW_UP_DATE("last_follow_up_date", "最近[写跟进]时间"),
    LAST_SENT_EMAIL_TIME("last_sent_email_time", "最近发件时间"),
    LAST_RECEIVED_EMAIL_TIME("last_received_email_time", "最近收件时间");

    private String columnName;
    private String nickName;
}
