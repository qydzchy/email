package com.ruoyi.customer.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubgroupColumnVO {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段别名
     */
    private String nickName;
}
