package com.ruoyi.customer.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerFollowUpRecordsListBO {

    /** 主键 */
    private Long id;

    /** 跟进类型 1.快速记录 2.电话 3.会面 4.社交平台 */
    private Integer followUpType;

    /** 跟进内容 */
    private String followUpContent;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;

    /** 跟进联系人ID */
    private Long followUpContact;

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
     * 评论
     */
    private String comment;
}
