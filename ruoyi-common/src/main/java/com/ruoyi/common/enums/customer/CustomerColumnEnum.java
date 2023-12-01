package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerColumnEnum {
    // 公司信息
    COMPANY_NAME("company_name", "公司名称", CustomerColumnCategoryEnum.COMPANY_INFO),
    SHORT_NAME("short_name", "简称", CustomerColumnCategoryEnum.COMPANY_INFO),
    CUSTOMER_NO("customer_no", "客户编号", CustomerColumnCategoryEnum.COMPANY_INFO),
    CUSTOMER_TAG("customer_tag", "客户标签", CustomerColumnCategoryEnum.COMPANY_INFO),
    COUNTRY_REGION("country_region", "国家地区", CustomerColumnCategoryEnum.COMPANY_INFO),
    TIMEZONE("timezone", "时区", CustomerColumnCategoryEnum.COMPANY_INFO),
    CUSTOMER_STAGE("customer_stage", "客户阶段", CustomerColumnCategoryEnum.COMPANY_INFO),
    CUSTOMER_SOURCE("customer_source", "客户来源", CustomerColumnCategoryEnum.COMPANY_INFO),
    PACKET("customer_packet", "分组", CustomerColumnCategoryEnum.COMPANY_INFO),
    PUBLICLEADS_GROUPS("publicleads_groups", "公海分组", CustomerColumnCategoryEnum.COMPANY_INFO),
    CUSTOMER_RATING("customer_rating", "客户星级", CustomerColumnCategoryEnum.COMPANY_INFO),
    SCALE("scale", "规模", CustomerColumnCategoryEnum.COMPANY_INFO),
    COMPANY_WEBSITE("company_website", "公司网址", CustomerColumnCategoryEnum.COMPANY_INFO),
    FAX("fax", "传真", CustomerColumnCategoryEnum.COMPANY_INFO),
    SPECIAL_PLANE("special_plane", "座机", CustomerColumnCategoryEnum.COMPANY_INFO),
    ADDRESS("address", "地址", CustomerColumnCategoryEnum.COMPANY_INFO),
    COMPANY_REMARKS("company_remarks", "公司备注", CustomerColumnCategoryEnum.COMPANY_INFO),
    FOLLOW_UP_PERSONNEL("follow_up_personnel", "跟进人", CustomerColumnCategoryEnum.COMPANY_INFO),
    //SOURCE_FOLLOW_UP_PERSONNEL("source_follow_up_personnel", "源跟进人", CustomerColumnCategoryEnum.COMPANY_INFO),
    CREATOR("creator", "创建人", CustomerColumnCategoryEnum.COMPANY_INFO),
    LAST_MODIFIED_BY("last_modified_by", "最近修改人", CustomerColumnCategoryEnum.COMPANY_INFO),
    FOLLOW_UP_NUMBER("follow_up_number", "跟进人数", CustomerColumnCategoryEnum.COMPANY_INFO),
    //ENTER_PUBLICLEADS_COUNT("enter_publicleads_count", "进入公海次数", CustomerColumnCategoryEnum.COMPANY_INFO),

    // 联系人信息
    NICK_NAME("nick_name", "昵称", CustomerColumnCategoryEnum.CONTACT_INFO),
    EMAIL("email", "邮箱", CustomerColumnCategoryEnum.CONTACT_INFO),
    RANK("rank", "职级", CustomerColumnCategoryEnum.CONTACT_INFO),
    PHONE("phone", "联系电话", CustomerColumnCategoryEnum.CONTACT_INFO),
    SOCIAL_PLATFORM("social_platform", "社交平台", CustomerColumnCategoryEnum.CONTACT_INFO),
    SEX("sex", "性别", CustomerColumnCategoryEnum.CONTACT_INFO),
    CONTACT_REMARKS("contact_remarks", "联系人备注", CustomerColumnCategoryEnum.CONTACT_INFO),

    // 时间日期
    CREATE_TIME("create_time", "创建时间", CustomerColumnCategoryEnum.DATE_TIME),
    DATA_UPDATE_TIME("data_update_time", "资料更新时间", CustomerColumnCategoryEnum.DATE_TIME),
    LAST_CONTACTED_AT("last_contacted_at", "最近联系时间", CustomerColumnCategoryEnum.DATE_TIME),
    LAST_FOLLOWUP_AT("last_followup_at", "最近跟进时间", CustomerColumnCategoryEnum.DATE_TIME),
    //NEXT_SCHEDULE_TIME("next_schedule_time", "下次日程时间", CustomerColumnCategoryEnum.DATE_TIME),
    LAST_PRIVATELEADS_ENTRY("last_privateleads_entry", "最近进入私海时间", CustomerColumnCategoryEnum.DATE_TIME),
    LAST_PUBLICLEADS_ENTRY("last_publicleads_entry", "最近进入公海时间", CustomerColumnCategoryEnum.DATE_TIME),
    NEXT_PUBLICLEADS_DATE("next_publicleads_date", "下次移入公海日期", CustomerColumnCategoryEnum.DATE_TIME),
    LAST_FOLLOW_UP_DATE("last_follow_up_date", "最近[写跟进]时间", CustomerColumnCategoryEnum.DATE_TIME),
    //LAST_SENT_EMAIL_TIME("last_sent_email_time", "最近发件时间", CustomerColumnCategoryEnum.DATE_TIME),
    //LAST_RECEIVED_EMAIL_TIME("last_received_email_time", "最近收件时间", CustomerColumnCategoryEnum.DATE_TIME)
    ;

    private String columnName;
    private String nickName;
    private CustomerColumnCategoryEnum customerColumnCategoryEnum;

    public static CustomerColumnEnum getEnum(String columnName) {
        if (columnName == null) return null;

        for (CustomerColumnEnum customerColumnEnum : CustomerColumnEnum.values()) {
            if (customerColumnEnum.getColumnName().equals(columnName)) {
                return customerColumnEnum;
            }
        }
        return null;
    }
}
