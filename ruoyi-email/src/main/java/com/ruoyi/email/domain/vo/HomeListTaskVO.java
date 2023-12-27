package com.ruoyi.email.domain.vo;

import lombok.Data;

@Data
public class HomeListTaskVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 连接状态: 1.正常 2.异常
     */
    private Integer connStatus;

    /**
     * 邮件数量
     */
    private Integer mailQuantity;
}
