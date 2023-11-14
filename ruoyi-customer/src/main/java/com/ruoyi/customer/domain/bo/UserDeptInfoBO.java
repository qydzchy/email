package com.ruoyi.customer.domain.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDeptInfoBO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;
}
