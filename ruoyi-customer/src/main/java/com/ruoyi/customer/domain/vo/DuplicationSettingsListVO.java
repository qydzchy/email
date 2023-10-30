package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class DuplicationSettingsListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 列ID
     */
    private Long columnId;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列别名
     */
    private String columnAlias;

    /**
     * 标志 0.未选 1.选中
     */
    private Boolean activeFlag;
}
