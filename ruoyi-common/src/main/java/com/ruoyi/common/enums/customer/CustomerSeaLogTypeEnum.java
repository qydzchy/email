package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerSeaLogTypeEnum {

    // 1.手动 2.自动
    MANUAL(1),
    AUTO(2);

    private Integer type;
}
