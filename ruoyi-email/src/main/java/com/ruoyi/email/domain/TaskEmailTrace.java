package com.ruoyi.email.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件追踪对象 mailbox_task_email_trace
 * 
 * @author tangJM.
 * @date 2023-09-17
 */
public class TaskEmailTrace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 发送邮件ID */
    @Excel(name = "邮件ID")
    private Long emailId;

    /** 北京时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "北京时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beijingTime;

    /** 当地时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "当地时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date localTime;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public void setBeijingTime(Date beijingTime)
    {
        this.beijingTime = beijingTime;
    }

    public Date getBeijingTime() 
    {
        return beijingTime;
    }
    public void setLocalTime(Date localTime) 
    {
        this.localTime = localTime;
    }

    public Date getLocalTime() 
    {
        return localTime;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("emailId", getEmailId())
            .append("beijingTime", getBeijingTime())
            .append("localTime", getLocalTime())
            .append("ip", getIp())
            .append("location", getLocation())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
