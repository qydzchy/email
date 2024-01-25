package com.ruoyi.email.domain.dto.email;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EmailPendingDTO {

    /**
     * 邮件ID
     */
    private List<Long> ids;

    /**
     * 是否待处理: 0.否 1.是
     */
    private Boolean pendingFlag;

    /**
     * 待处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pendingTime;
}
