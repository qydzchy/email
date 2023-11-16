package com.ruoyi.customer.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 客户查重筛选字段
 */
@Data
@Builder
public class CustomerDuplicateFilterColumnListVO {

    private String columnName;

    private String columnAlias;
}
