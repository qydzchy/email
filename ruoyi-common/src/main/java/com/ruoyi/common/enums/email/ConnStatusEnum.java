package com.ruoyi.common.enums.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ConnStatusEnum {

    // 连接状态: 1.正常 2.异常
    NORMAL(1), ABNORMAL(2);

    private int type;
}
