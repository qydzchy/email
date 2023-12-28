package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransceiverRuleConditionTypeEnum {

    CONTAINS(1, "包含"),
    NOT_CONTAINS(2, "不包含"),
    BELONGS_TO(3, "隶属于"),
    EQUALS(4, "等于"),
    NOT_EQUALS(5, "不等于");

    private int type;
    private String desc;

    public static TransceiverRuleConditionTypeEnum getByType(int type) {
        for (TransceiverRuleConditionTypeEnum value : TransceiverRuleConditionTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return null;
    }
}
