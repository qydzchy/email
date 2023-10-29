package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PacketListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 父分组名称
     */
    private String parentName;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 可用成员 1.全部成员 2.特定成员
     */
    private Integer availableMember;

    /**
     * 指定成员
     */
    private String designatedMember;

    private List<PacketListVO> children = new ArrayList<>();
}
