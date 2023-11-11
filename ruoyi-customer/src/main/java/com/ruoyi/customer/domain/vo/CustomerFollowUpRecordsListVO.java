package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerFollowUpRecordsListVO {

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
    private String operatorTime;

    /**
     * 评论
     */
    private List<FollowUpRecordsCommentListVO> commentList;

    /**
     * 可编辑的 false否 true是
     * @return
     */
    public Boolean getEditable() {
        // 获取当前时间
        Date currentTime = new Date();
        // 计算时间差
        long timeDifference = currentTime.getTime() - submissionTime.getTime();
        long hoursDifference = timeDifference / (60 * 60 * 1000); // 毫秒转小时
        if (hoursDifference >= 24) {
            return false;
        } else {
            return true;
        }
    }
}
