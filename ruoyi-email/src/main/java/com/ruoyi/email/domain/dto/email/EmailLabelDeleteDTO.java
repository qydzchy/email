package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailLabelDeleteDTO {

    @NotNull(message = "邮件id不能为空")
    private Long emailId;

    @NotNull(message = "标签id不能为空")
    private Long labelId;
}
