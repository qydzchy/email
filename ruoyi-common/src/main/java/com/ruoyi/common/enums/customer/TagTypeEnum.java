package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TagTypeEnum {
    // 1.公司 2.个人
    COMPANY(1), PERSONAL(2);

    private int type;
}
