package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author tangJM.
 * @date 2021/10/14
 * @description
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProxyTypeEnum {
    HTTP(1),
    SOCKS(2);

    private int type;

    public static ProxyTypeEnum getByType(Integer type) {
        if (type == null) {
            return null;
        }

        for (ProxyTypeEnum value : ProxyTypeEnum.values()) {
            if (value.getType() == type.intValue()) {
                return value;
            }
        }
        return null;
    }
}
