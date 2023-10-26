package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PublicleadsRulesTypeEnum {

    // 客户状态-类型1.未联系 2.未成交

    NOT_CONTACT(1, "未联系"),
    NOT_DEAL(2, "未成交");

    private int type;
    private String typeName;

    public static PublicleadsRulesTypeEnum getByType(Integer type) {
        if (type == null) return null;

        for (PublicleadsRulesTypeEnum value : PublicleadsRulesTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }

        return null;
    }
}
