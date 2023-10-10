package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 批量更新邮件为已读邮件
 */
@Data
public class EmailReadFlagBatchUpdateDTO {

    @NotNull(message = "请选择邮件")
    private List<Long> ids;

    @NotNull(message = "是否已读状态不能为空")
    private Boolean readFlag;
}
