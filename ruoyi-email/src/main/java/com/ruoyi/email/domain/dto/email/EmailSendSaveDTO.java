package com.ruoyi.email.domain.dto.email;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class EmailSendSaveDTO {

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    @NotNull(message = "邮件任务ID不能为空")
    private Long taskId;

    /** 发件人 */
    private String fromer;

    /** 收件人JSON */
    private String receiver;

    /** 密送人JSON,格式和收件人一样 */
    private String bcc;

    /** 抄送人JSON,格式和收件人一样 */
    private String cc;

    /** 邮件主题 */
    private String title;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件附件ID列表
     */
    private List<Long> attachmentIdList;

    /**
     * 是否追踪
     */
    private Boolean traceFlag;

    /** 定时发送: 0.否 1.是 */
    private Boolean delayedTxFlag;

    /** 收件人所在时区 */
    private String recipientTimeZone;

    /** 收件人当地时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recipientLocalTime;

    /** 是否待处理: 0.否 1.是 */
    /*@NotNull(message = "是否待处理不能为空")*/
    private Boolean pendingFlag;

    /** 待处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pendingTime;

    /** 是否固定: 0.否 1.是 */
    private Integer fixedFlag;
}
