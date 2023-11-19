package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class CustomerImportListVO {

    private Long id;

    /** 导入类型 1.客户列表 2.公海客户 */
    private Integer importType;

    /** 导入状态 1.进行中 2.成功 3.失败 */
    private Integer importStatus;

    /** 预见导入数 */
    private Integer expectedImportCount;

    /** 成功导入数 */
    private Integer successImportCount;

    /** 导入失败数 */
    private Integer failedImportCount;

    /**
     * 操作者ID
     */
    private Long createId;

    /**
     * 操作人
     */
    private String createBy;
}
