package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class SegmentUserListVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 客群数
     */
    private Integer segmentCount;
}
