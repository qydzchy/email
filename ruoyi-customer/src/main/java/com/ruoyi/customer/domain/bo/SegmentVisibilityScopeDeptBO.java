package com.ruoyi.customer.domain.bo;

import lombok.Data;

import java.util.List;

@Data
public class SegmentVisibilityScopeDeptBO {

    /**
     * true所有部门 false指定部
     */
    private Boolean allFlag;

    /**
     * 指定部门ID
     */
    private List<Long> deptIds;
}
