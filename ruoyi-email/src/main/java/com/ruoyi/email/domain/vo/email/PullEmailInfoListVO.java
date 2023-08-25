package com.ruoyi.email.domain.vo.email;

import lombok.Data;

import java.text.SimpleDateFormat;
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

    /**
     * 邮件内容
     */
    private String content;

    /**
     *
     * @return
     */
    public String getSendTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(sendDate);
    }
}
