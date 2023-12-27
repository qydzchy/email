package com.ruoyi.email.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AttachmentListVO {

    private Long id;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件大小
     */
    private Long size;

}
