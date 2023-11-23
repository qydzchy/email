package com.ruoyi.customer.domain.bo;

import lombok.Data;

@Data
public class RoleDeptSimpleInfoVO {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5：仅本人数据权限）
     */
    private Integer dataScope;

    /**
     * 角色部门ID
     */
    private Long roleDeptId;
}
