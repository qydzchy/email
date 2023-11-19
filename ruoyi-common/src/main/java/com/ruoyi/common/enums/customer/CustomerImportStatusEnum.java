package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerImportStatusEnum {

    IN_PROGRESS(1),
    SUCCESS(2),
    FAILURE(3);

    private int status;
}
