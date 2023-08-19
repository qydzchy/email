package com.ruoyi.email.domain.dto.folder;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FolderSaveOrUpdateDTO {

    private Long id;

    @NotNull(message = "父文件夹ID不能为空")
    private Long parentFolderId;

    @NotBlank(message = "文件夹名称不能为空")
    private String name;
}
