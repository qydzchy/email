package com.ruoyi.email.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentUploadVO {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 附件大小
     */
    private Long size;
}
