package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailLabelMoveDTO {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotNull(message = "标签ID不能为空")
    private Long labelId;
}
