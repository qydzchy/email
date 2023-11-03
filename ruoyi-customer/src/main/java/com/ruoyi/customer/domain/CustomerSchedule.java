package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户日程对象 customer_customer_schedule
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
public class CustomerSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 日程内容 */
    @Excel(name = "日程内容")
    private String scheduleContent;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 全天 0.否 1.是 */
    @Excel(name = "全天 0.否 1.是")
    private Integer allDayFlag;

    /** 日程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日程开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduleStartTime;

    /** 日程结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日程结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduleEndTime;

    /**
     * 日程是否完成 0.否 1.是
     */
    private Boolean completedFlag;

    /** 周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义 */
    @Excel(name = "周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义")
    private Integer recurringSchedule;

    /** 自定义周期-值 */
    @Excel(name = "自定义周期-值")
    private Long customCycleValue;

    /** 自定义周期-类型 1.天 2.周 3.月 */
    @Excel(name = "自定义周期-类型 1.天 2.周 3.月")
    private Integer customCycleType;

    /** 周期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "周期结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cycleEndTime;

    /** 提醒时间 [{"reminderTimeType": 1, "reminderTimeValue": "2023-11-03 00:00:00"}]
reminderTimeType：提醒时间类型 1.不提醒 2.当天开始(上午9:00) 3.1天前(上午9:00) 4.2天前(上午9:00) 5.1周前(上午9:00) 6.自定义
reminderTimeValue：提醒时间 */
    @Excel(name = "提醒时间")
    private String reminderTime;

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long createId;

    /** 更新者ID */
    @Excel(name = "更新者ID")
    private Long updateId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setScheduleContent(String scheduleContent) 
    {
        this.scheduleContent = scheduleContent;
    }

    public String getScheduleContent() 
    {
        return scheduleContent;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setAllDayFlag(Integer allDayFlag) 
    {
        this.allDayFlag = allDayFlag;
    }

    public Integer getAllDayFlag() 
    {
        return allDayFlag;
    }
    public void setScheduleStartTime(Date scheduleStartTime) 
    {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Date getScheduleStartTime() 
    {
        return scheduleStartTime;
    }
    public void setScheduleEndTime(Date scheduleEndTime) 
    {
        this.scheduleEndTime = scheduleEndTime;
    }

    public Date getScheduleEndTime() 
    {
        return scheduleEndTime;
    }
    public void setRecurringSchedule(Integer recurringSchedule) 
    {
        this.recurringSchedule = recurringSchedule;
    }

    public Boolean getCompletedFlag() {
        return completedFlag;
    }

    public void setCompletedFlag(Boolean completedFlag) {
        this.completedFlag = completedFlag;
    }

    public Integer getRecurringSchedule()
    {
        return recurringSchedule;
    }
    public void setCustomCycleValue(Long customCycleValue) 
    {
        this.customCycleValue = customCycleValue;
    }

    public Long getCustomCycleValue() 
    {
        return customCycleValue;
    }
    public void setCustomCycleType(Integer customCycleType) 
    {
        this.customCycleType = customCycleType;
    }

    public Integer getCustomCycleType() 
    {
        return customCycleType;
    }
    public void setCycleEndTime(Date cycleEndTime) 
    {
        this.cycleEndTime = cycleEndTime;
    }

    public Date getCycleEndTime() 
    {
        return cycleEndTime;
    }
    public void setReminderTime(String reminderTime) 
    {
        this.reminderTime = reminderTime;
    }

    public String getReminderTime() 
    {
        return reminderTime;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }
    public void setUpdateId(Long updateId) 
    {
        this.updateId = updateId;
    }

    public Long getUpdateId() 
    {
        return updateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scheduleContent", getScheduleContent())
            .append("color", getColor())
            .append("allDayFlag", getAllDayFlag())
            .append("scheduleStartTime", getScheduleStartTime())
            .append("scheduleEndTime", getScheduleEndTime())
            .append("recurringSchedule", getRecurringSchedule())
            .append("customCycleValue", getCustomCycleValue())
            .append("customCycleType", getCustomCycleType())
            .append("cycleEndTime", getCycleEndTime())
            .append("reminderTime", getReminderTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createId", getCreateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
