package com.ruoyi.customer.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeamMembersListVO {

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
        TeamMembersListVO that = (TeamMembersListVO) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
