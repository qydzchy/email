package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum VisibilityScopeTypeEnum {
    // 可见范围-类型 1.全公司可见 2.全部部门 3.指定部门 4.全部人员 5.指定人员

    ALL_COMPANY_VISIBLE(1), ALL_DEPARTMENTS(2), SPECIFIC_DEPARTMENT(3), ALL_EMPLOYEES(4), SPECIFIC_EMPLOYEES(5);

    private Integer type;


    public static VisibilityScopeTypeEnum getByType(Integer type) {
        for (VisibilityScopeTypeEnum value : VisibilityScopeTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
