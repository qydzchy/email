package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PublicleadsClaimPeriodEnum {
    DAILY(1),
    WEEKLY(2),
    MONTHLY(3),
    YEARLY(4);

    private int claimPeriod;

    public static PublicleadsClaimPeriodEnum getByClaimPeriod(Integer claimPeriod) {
        if (claimPeriod == null) return null;

        for (PublicleadsClaimPeriodEnum value : PublicleadsClaimPeriodEnum.values()) {
            if (value.getClaimPeriod() == claimPeriod) {
                return value;
            }
        }
        return null;
    }
}
