package com.ruoyi.customer.domain.bo;

import lombok.Data;

@Data
public class SegmentVisibilityScopeBO {

    /**
     * 部门
     */
    private SegmentVisibilityScopeDeptBO dept;

    /**
     * 用户
     */
    private SegmentVisibilityScopeUserBO user;
}
