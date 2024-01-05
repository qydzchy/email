package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ConditionTypeEnum {

    INCLUDE(1, "包含"),
    NOT_INCLUDE(2, "不包含"),
    BELONGS_TO(3, "隶属于"),
    EQUALS(4, "等于"),
    NOT_EQUALS(5, "不等于");

    private Integer type;
    private String name;

    public static ConditionTypeEnum getEnum(Integer type) {
        if (type == null) return null;

        for (ConditionTypeEnum conditionTypeEnum : ConditionTypeEnum.values()) {
            if (conditionTypeEnum.getType().equals(type)) {
                return conditionTypeEnum;
            }
        }
        return null;
    }
}
