package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DataScopeEnum {

    ALL(1, "全部数据权限"),
    CUSTOM(2, "自定数据权限"),
    DEPARTMENT(3, "本部门数据权限"),
    DEPARTMENT_AND_BELOW(4, "本部门及以下数据权限"),
    ONLY_SELF(5, "仅本人数据权限");

    private int code;
    private String description;

    public static DataScopeEnum getByCode(Integer code) {
        if (code == null) return null;

        for (DataScopeEnum dataScopeEnum : DataScopeEnum.values()) {
            if (dataScopeEnum.getCode() == code) {
                return dataScopeEnum;
            }
        }

        return null;
    }
}
