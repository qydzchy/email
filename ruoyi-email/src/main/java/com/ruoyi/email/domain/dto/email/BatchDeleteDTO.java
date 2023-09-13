package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BatchDeleteDTO {

    @NotNull(message = "请选择要删除的数据")
    private List<Long> ids;
}
