package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerSeaTypeEnum {

    // 私海/公海类型 1.私海 2.公海
    PRIVATE_LEADS(1), PUBLIC_LEADS(2);

    private Integer type;
}
