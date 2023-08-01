package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProtocolTypeEnum {

    IMAP(1), POP3(2), EXCHANGE(3), SMTP(4);

    private int type;


    public static ProtocolTypeEnum getByType(Integer type) {
        if (type == null) {
            return null;
        }

        for (ProtocolTypeEnum value : ProtocolTypeEnum.values()) {
            if (value.getType() == type.intValue()) {
                return value;
            }
        }
        return null;
    }
}
