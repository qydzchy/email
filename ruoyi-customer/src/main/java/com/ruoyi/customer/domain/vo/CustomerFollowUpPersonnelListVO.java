package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class CustomerFollowUpPersonnelListVO {

    /**
     * 跟进人ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 跟进人ID
     */
    private Long userId;

    /**
     * 跟进人名称
     */
    private String userName;

    /**
     * 部门ID
     */
    private Long deptId;
}
