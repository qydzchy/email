package com.ruoyi.email.domain.vo.email;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 批量移动邮件到指定文件夹
 */
@Data
public class EmailFolderMoveDTO {

    @NotNull(message = "请选择邮件")
    private List<Long> ids;

    @NotNull(message = "文件夹ID不能为空")
    private Long folderId;

}
