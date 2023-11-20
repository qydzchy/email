package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDocumentListVO {

    /** 主键 */
    private Long id;

    /** 文件名称 */
    private String name;

    /** 文件路径 */
    private String path;

    /** 文件后缀 */
    private String extension;

    /** 文件大小 */
    private Long size;

    /** 关联类型 1.邮件附件 2.手动上传 */
    private Integer type;

    /** 创建者ID */
    private Long createId;

    /**
     * 创建人昵称
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
