package com.ruoyi.email.domain.vo.task;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestTaskVO {

    /**
     * 连接状态
     */
    private Integer connStatus;

    /**
     * 连接异常原因
     */
    private String connExceptionReason;
}
