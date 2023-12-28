package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransceiverRuleColumnNameEnum {

    SENDER("sender", "发件人"),
    RECIPIENT("recipient", "收件人")   ,
    CC("cc", "抄送人"),
    SUBJECT("subject", "主题"),
    BODY("body", "正文");

    private String columnName;
    private String columnAlias;

    public static TransceiverRuleColumnNameEnum getByColumnName(String columnName) {
        for (TransceiverRuleColumnNameEnum value : TransceiverRuleColumnNameEnum.values()) {
            if (value.getColumnName().equals(columnName)) {
                return value;
            }
        }
        return null;
    }
}
