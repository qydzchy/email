package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ConditionTypeEnum {

    EQUALS(1, "等于"),
    NOT_EQUALS(2, "不等于"),
    IS_NULL(3, "为空"),
    NOT_NULL(4, "不为空"),
    IN(5, "属于"),
    NOT_IN(6, "不属于"),
    BEFORE(7, "早于"),
    AFTER(8, "晚于");

    private int type;
    private String description;

    public static ConditionTypeEnum getByType(Integer type) {
        if (type == null) return null;

        for (ConditionTypeEnum value : ConditionTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return null;
    }
}
