package com.ruoyi.email.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestTaskVO {

    /**
     * 连接状态
     */
    private Boolean connStatus;

    /**
     * 连接异常原因
     */
    private String connExceptionReason;
}
