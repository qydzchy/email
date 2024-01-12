package com.ruoyi.email.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class EmailSimpleBO {

    /**
     * 发件人
     */
    private String fromer;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 抄送人
     */
    private String cc;

    /**
     * 主题
     */
    private String subject;

    /**
     * 正文
     */
    private String body;

    /**
     * 发送时间
     */
    private Date sendDate;
}
