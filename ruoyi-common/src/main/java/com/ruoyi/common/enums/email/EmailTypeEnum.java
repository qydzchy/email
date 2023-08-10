package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 邮件类型
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EmailTypeEnum {
    PULL(1), SEND(2);

    private int type;
}
