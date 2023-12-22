package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ImportStatusEnum {

    PROCESSING(1, "正在处理"),
    SUCCESS(2, "导入成功"),
    FAILURE(3, "导入失败");

    private int status;
    private String name;

    public static String getByStatus(Integer status) {
        if (status == null) return null;

        for (ImportStatusEnum importStatusEnum : ImportStatusEnum.values()) {
            if (importStatusEnum.getStatus() == status.intValue()) {
                return importStatusEnum.getName();
            }
        }

        return null;
    }
}
