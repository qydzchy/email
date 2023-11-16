package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 客户文档类型枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerDocumentTypeEnum {

    EMAIL_ATTACHMENT(1, "邮件附件"),
    MANUAL_UPLOAD(2, "手动上传");

    private Integer type;
    private String description;
}
