package com.ruoyi.customer.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerFollowUpRecordsListBO {

    /** 主键 */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /** 跟进类型 1.快速记录 2.电话 3.会面 4.社交平台 */
    private Integer followUpType;

    /** 跟进内容 */
    private String followUpContent;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;

    /** 跟进联系人ID */
    private Long followUpContactId;

    /** 下次跟进日程 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextFollowUpSchedule;

    /** 全天 0.否 1.是 */
    private Integer allDayFlag;

    /** 日程内容 */
    private String scheduleContent;

    /** 颜色 */
    private String color;

    /** 备注 */
    private String remarks;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String operatorTime;

    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 评论
     */
    private String comment;

    /**
     * 评论操作人
     */
    private String commentOperator;

    /**
     * 评论操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String commentOperatorTime;
}
