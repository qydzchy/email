package com.ruoyi.customer.domain.bo;

import lombok.Data;

import java.util.List;

@Data
public class SegmentVisibilityScopeUserBO {

    /**
     * true所有用户 false指定用户
     */
    private Boolean allFlag;

    /**
     * 指定用户ID
     */
    private List<Long> userIds;
}
