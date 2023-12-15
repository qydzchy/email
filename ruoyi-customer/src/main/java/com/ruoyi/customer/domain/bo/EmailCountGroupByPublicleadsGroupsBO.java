package com.ruoyi.customer.domain.bo;

import lombok.Data;

@Data
public class EmailCountGroupByPublicleadsGroupsBO {

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 公海分组ID
     */
    private Long publicleadsGroupsId;
}
