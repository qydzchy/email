package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ContactInfoEnum {

    NICK_NAME("nick_name", "昵称"),
    EMAIL("email", "邮箱"),
    RANK("rank", "职级"),
    PHONE("phone", "联系电话"),
    SOCIAL_PLATFORM("social_platform", "社交平台"),
    SEX("sex", "性别"),
    CONTACT_REMARKS("contact_remarks", "联系人备注");

    private String columnName;
    private String nickName;
}
