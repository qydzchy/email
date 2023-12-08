package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class PublicleadsGroupsListVO {

    private Long id;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组成员
     */
    private String groupMember;

    /**
     * 是否为默认分组
     */
    private Boolean defaultGroupFlag;
}
