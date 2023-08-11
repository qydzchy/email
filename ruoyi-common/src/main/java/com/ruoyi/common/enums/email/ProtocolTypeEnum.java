package com.ruoyi.common.enums.email;

import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProtocolTypeEnum {

    IMAP(1, "IMAP"), POP3(2, "POP"), EXCHANGE(3, "EXCHANGE"), SMTP(4, "SMTP");

    private int type;
    private String name;


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
