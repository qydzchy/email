package com.ruoyi.common.enums.email;

import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProtocolTypeEnum {

    IMAP(1, "imap"), POP3(2, "pop"), EXCHANGE(3, "exchange"), SMTP(4, "smtp");

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
