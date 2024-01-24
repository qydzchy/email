package com.ruoyi.email.domain.vo;

import lombok.Data;

@Data
public class SubordinateListVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 部门ID
     */
    private Long deptId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubordinateListVO that = (SubordinateListVO) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
