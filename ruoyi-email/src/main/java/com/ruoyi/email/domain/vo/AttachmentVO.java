package com.ruoyi.email.domain.vo;

import lombok.Data;

@Data
public class AttachmentVO {

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件地址路径
     */
    private String path;
}
