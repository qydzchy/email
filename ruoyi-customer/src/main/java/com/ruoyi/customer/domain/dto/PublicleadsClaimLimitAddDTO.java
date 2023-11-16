package com.ruoyi.customer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PublicleadsClaimLimitAddDTO {

    /**
     * 人员ID
     */
    private List<Long> userIdList;

    /** 领取上限 */
    private Integer claimLimit;

    /** 上限周期 1.每日2.每周3.每月4.每年 */
    private Integer claimPeriod;
}
