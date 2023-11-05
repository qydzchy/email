package com.ruoyi.customer.domain.bo;

import lombok.Data;


@Data
public class PublicleadsGroupsListBO {

    private Long id;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 是否为默认分组
     */
    private Boolean defaultGroupFlag;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;
}
