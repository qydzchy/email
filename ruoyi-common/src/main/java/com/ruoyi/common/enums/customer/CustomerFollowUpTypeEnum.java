package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerFollowUpTypeEnum {

/**
     * 快速记录
     */
    QUICK_RECORD(1, "快速记录"),
    /**
     * 电话
     */
    PHONE(2, "电话"),
    /**
     * 会面
     */
    MEETING(3, "会面"),
    /**
     * 社交平台
     */
    SOCIAL_PLATFORM(4, "社交平台");

    private Integer code;
    private String name;

    public static String getNameByCode(Integer code) {
        for (CustomerFollowUpTypeEnum value : CustomerFollowUpTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value.getName();
            }
        }
        return null;
    }
}
