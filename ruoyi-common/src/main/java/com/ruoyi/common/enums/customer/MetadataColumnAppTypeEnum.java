package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MetadataColumnAppTypeEnum {

    //应用类型 1.客户查重 2.客户列表 3.公海客户
    DUPLICATION_SETTINGS(1), CUSTOMER_LIST(2), PUBLICLEADS_CUSTOMER(3);

    private Integer appType;
}
