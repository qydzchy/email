package com.ruoyi.email.domain.bo;

import lombok.Data;

@Data
public class DealingEmailListBO {

    private Long id;

    /**
     * 类型 1.收件 2.发件
     */
    private Integer type;

    /**
     * 发送时间
     */
    private String sendDate;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签ID
     */
    private Long labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签颜色
     */
    private String labelColor;

    /**
     * 标签类型 1.系统标签 2.自定义标签
     */
    private Integer labelType;
}
