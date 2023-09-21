package com.ruoyi.email.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 邮件附件
 */
@Data
public class EmailAttachmentBO {

    /**
     * 附件ID
     */
    private Long id;

    /**
     * 邮件ID
     */
    private Long emailId;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件大小
     */
    private Integer size;

    /**
     * 附件路径
     */
    @JsonIgnore
    private String path;

    public Integer getSize() {
        return size != null ? size : 0;
    }
}
