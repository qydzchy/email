package com.ruoyi.customer.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerScheduleAddOrUpdateDTO {

    /** 主键 */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /** 日程内容 */
    private String scheduleContent;

    /** 颜色 */
    private String color;

    /** 全天 0.否 1.是 */
    private Integer allDayFlag;

    /** 日程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduleStartTime;

    /** 日程结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduleEndTime;

    /**
     * 日程是否完成 0.否 1.是
     */
    private Boolean completedFlag;

    /** 周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义 */
    private Integer recurringSchedule;

    /** 自定义周期-值 */
    private Long customCycleValue;

    /** 自定义周期-类型 1.天 2.周 3.月 */
    private Integer customCycleType;

    /** 周期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cycleEndTime;

    /** 提醒时间 [{"reminderTimeType": 1, "reminderTimeValue": "2023-11-03 00:00:00"}]
     reminderTimeType：提醒时间类型 1.不提醒 2.当天开始(上午9:00) 3.1天前(上午9:00) 4.2天前(上午9:00) 5.1周前(上午9:00) 6.自定义
     reminderTimeValue：提醒时间 */
    private String reminderTime;

    /**
     * 参与人
     */
    private List<Long> userIds;
}
