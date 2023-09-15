package com.ruoyi.email.domain.vo.email;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 批量更新邮件为垃圾邮件
 */
@Data
public class EmailSpamFlagBatchUpdateDTO {

    @NotNull(message = "请选择邮件")
    private List<Long> ids;

    @NotNull(message = "垃圾邮件状态不能为空")
    private Boolean spamFlag;
}
