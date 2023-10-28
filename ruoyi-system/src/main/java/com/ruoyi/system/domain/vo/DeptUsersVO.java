package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeptUsersVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 1. 部门 2. 用户
     */
    private Integer type;

    /**
     * 子级
     */
    private List<DeptUsersVO> children;
}
