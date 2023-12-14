package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CustomerActivityEnum {

    WITHIN_3_DAYS(1, "3天内有联系"),
    BETWEEN_3_AND_7_DAYS(2, "3-7天内有联系"),
    BETWEEN_7_AND_30_DAYS(3, "7-30天内有联系"),
    BETWEEN_30_AND_90_DAYS(4, "30-90天内有联系"),
    OVER_90_DAYS(5, "超过90天未联系");

    private Integer type;
    private String name;
}
