package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class DuplicationSettingsListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 字段ID
     */
    private Long fieldId;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 标志 0.未选 1.选中
     */
    private Boolean activeFlag;
}
