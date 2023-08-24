package com.ruoyi.email.domain.vo.email;

import lombok.Data;

import java.util.Date;

@Data
public class PullEmailInfoListVO {

    private Long id;

    /**
     * 邮件标题
     */
    private String title;

    /**
     * 发件人
     */
    private String fromer;

    /**
     * 发送时间
     */
    private Date sendDate;
}
