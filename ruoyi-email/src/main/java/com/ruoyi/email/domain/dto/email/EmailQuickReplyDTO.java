package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmailQuickReplyDTO {

    /**
     * 邮件ID
     */
    @NotNull(message = "邮件ID不能为空")
    private Long id;

    @NotBlank(message = "邮件内容不能为空")
    private String content;
}
