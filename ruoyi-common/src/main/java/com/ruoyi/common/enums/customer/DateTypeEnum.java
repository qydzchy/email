package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DateTypeEnum {

    SPECIFIC_DATE_RANGE(1), DYNAMIC_DATE_RANGE(2);

    private int type;

    public static DateTypeEnum getByType(Integer type) {
        if (type == null) return null;

        for (DateTypeEnum value : DateTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return null;
    }
}
