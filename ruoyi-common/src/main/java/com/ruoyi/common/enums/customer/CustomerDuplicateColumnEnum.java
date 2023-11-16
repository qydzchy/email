package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerDuplicateColumnEnum {
    ALL("all", "全部"),
    COMPANY_NAME("company_name", "公司名称/简称"),
    CUSTOMER_NO("customer_no", "客户编号"),
    EMAIL("email", "邮箱"),
    EMAIL_SUFFIX("email_suffix", "邮箱后缀"),
    CONTACT_NAME("contact_name", "联系人名称"),
    PHONE_NUMBER("phone_number", "联系电话"),
    SOCIAL_MEDIA_ACCOUNT("social_media_account", "社交账号");

    private String columnName;
    private String columnAlias;
}
