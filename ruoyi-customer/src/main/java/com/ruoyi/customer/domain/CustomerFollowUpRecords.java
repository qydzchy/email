package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户写跟进对象 customer_customer_follow_up_records
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public class CustomerFollowUpRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /** 跟进类型 1.快速记录 2.电话 3.会面 4.社交平台 */
    @Excel(name = "跟进类型 1.快速记录 2.电话 3.会面 4.社交平台")
    private Integer followUpType;

    /** 跟进内容 */
    @Excel(name = "跟进内容")
    private String followUpContent;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;

    /** 跟进联系人ID */
    @Excel(name = "跟进联系人ID")
    private Long followUpContactId;

    /** 下次跟进日程 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下次跟进日程", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date nextFollowUpSchedule;

    /** 全天 0.否 1.是 */
    @Excel(name = "全天 0.否 1.是")
    private Boolean allDayFlag;

    /** 日程内容 */
    @Excel(name = "日程内容")
    private String scheduleContent;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setFollowUpType(Integer followUpType)
    {
        this.followUpType = followUpType;
    }

    public Integer getFollowUpType() 
    {
        return followUpType;
    }
    public void setFollowUpContent(String followUpContent) 
    {
        this.followUpContent = followUpContent;
    }

    public String getFollowUpContent() 
    {
        return followUpContent;
    }
    public void setSubmissionTime(Date submissionTime) 
    {
        this.submissionTime = submissionTime;
    }

    public Date getSubmissionTime() 
    {
        return submissionTime;
    }
    public Long getFollowUpContactId() {
        return followUpContactId;
    }

    public void setFollowUpContactId(Long followUpContactId) {
        this.followUpContactId = followUpContactId;
    }

    public void setNextFollowUpSchedule(Date nextFollowUpSchedule)
    {
        this.nextFollowUpSchedule = nextFollowUpSchedule;
    }

    public Date getNextFollowUpSchedule() 
    {
        return nextFollowUpSchedule;
    }

    public Boolean getAllDayFlag() {
        return allDayFlag;
    }

    public void setAllDayFlag(Boolean allDayFlag) {
        this.allDayFlag = allDayFlag;
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
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("followUpType", getFollowUpType())
            .append("followUpContent", getFollowUpContent())
            .append("submissionTime", getSubmissionTime())
            .append("followUpContactId", getFollowUpContactId())
            .append("nextFollowUpSchedule", getNextFollowUpSchedule())
            .append("allDayFlag", getAllDayFlag())
            .append("scheduleContent", getScheduleContent())
            .append("color", getColor())
            .append("remarks", getRemarks())
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
