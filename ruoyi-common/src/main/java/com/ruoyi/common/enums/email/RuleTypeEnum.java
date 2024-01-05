package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RuleTypeEnum {

    PULL(1), SEND(2);

    private int type;
}
