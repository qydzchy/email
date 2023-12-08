package com.ruoyi.customer.domain.vo;

import com.ruoyi.system.domain.vo.UserInfoVO;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PublicleadsGroupsListVO {

    private Long id;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组成员信息
     */
    private List<Map<String, Object>> groupUserList;

    /**
     * 是否为默认分组
     */
    private Boolean defaultGroupFlag;
}
