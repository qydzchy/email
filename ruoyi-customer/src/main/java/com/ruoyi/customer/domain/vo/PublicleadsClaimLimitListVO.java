package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class PublicleadsClaimLimitListVO {

    /**
     * ID
     */
    private Long id;

    /** 人员ID */
    private Long userId;

    /**
     * 人员昵称
     */
    private String nickName;

    /** 领取上限 */
    private Long claimLimit;

    /** 上限周期 1.每日2.每周3.每月4.每年 */
    private Integer claimPeriod;
}
