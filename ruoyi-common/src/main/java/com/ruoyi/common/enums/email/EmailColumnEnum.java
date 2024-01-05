package com.ruoyi.common.enums.email;

import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EmailColumnEnum {

    FROMER("fromer", "如果发件人"),
    RECEIVER("receiver", "如果收件人"),
    CC("cc", "如果抄送人"),
    SUBJECT("subject", "如果主题中"),
    BODY("body", "如果正文中");

    private String columnName;
    private String nickName;

    public static EmailColumnEnum getEnum(String columnName) {
        if (StringUtils.isBlank(columnName)) return null;

        for (EmailColumnEnum emailColumnEnum : EmailColumnEnum.values()) {
            if (emailColumnEnum.getColumnName().equals(columnName)) {
                return emailColumnEnum;
            }
        }
        return null;
    }
}
