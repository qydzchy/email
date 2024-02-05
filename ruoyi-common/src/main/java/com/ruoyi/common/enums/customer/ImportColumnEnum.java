package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 导入列枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ImportColumnEnum {
    COMPANY_NAME("companyName", "公司名称"),
    CONTACT_NICK_NAME("contactNickName", "联系人昵称"),
    CONTACT_EMAIL("contactEmail", "联系人邮箱"),
    SHORT_NAME("shortName", "公司简称"),
    COUNTRY_REGION("countryRegion", "国家地区"),
    TAG("tag", "客户标签"),
    STAGE("stage", "客户阶段"),
    SOURCE("source", "客户来源"),
    COMPANY_WEBSITE("companyWebsite", "公司网址"),
    PHONE("phone", "座机"),
    ADDRESS("address", "详细地址"),
    COMPANY_REMARKS("companyRemarks", "公司备注"),
    CUSTOMER_NO("customerNo", "客户编号"),
    CONTACT_PHONE("contactPhone", "联系人电话"),
    FACEBOOK("facebook", "facebook"),
    TWITTER("twitter", "twitter"),
    LINKED_IN("linkedIn", "linkedIn"),
    ALI_TM("aliTM", "阿里TM"),
    WHATSAPP("Whatsapp", "Whatsapp"),
    SKYPE("Skype", "Skype"),
    WECHAT("Wechat", "Wechat"),
    MESSENGER("Messenger", "Messenger"),
    LINE("Line", "Line"),
    POSITION("position", "职位"),
    SEX("sex", "性别"),
    CONTACT_REMARKS("contactRemarks", "联系人备注"),
    FOLLOW_UP_PERSONNEL("followUpPersonnel", "跟进人"),
    SCALE("scale", "规模"),
    LAST_PRIVATELEADS_ENTRY("last_privateleads_entry", "最近进入私海时间"),
    BIRTHDAY("birthday", "生日"),
    RECENT_ACTIVITY("recentActivity", "最近动态"),
    TIMEZONE("timezone", "时区"),
    ORIGINAL_FOLLOW_UP_PERSONNEL("originalFollowUpPersonnel", "原跟进人"),
    UPDATE_BY("updateBy", "最近修改人"),
    PUBLICLEADS_REASON("publicleadsReason", "移入公海原因"),
    LAST_FOLLOWUP_AT("lastFollowupAt", "最近跟进时间");

    private String columnName;

    private String columnAlias;

    public static ImportColumnEnum getByColumnName(String columnName) {
        for (ImportColumnEnum value : ImportColumnEnum.values()) {
            if (value.getColumnName().equals(columnName)) {
                return value;
            }
        }
        return null;
    }

    public static ImportColumnEnum getByColumnAlias(String columnAlias) {
        for (ImportColumnEnum value : ImportColumnEnum.values()) {
            if (value.getColumnAlias().equals(columnAlias)) {
                return value;
            }
        }
        return null;
    }
}
