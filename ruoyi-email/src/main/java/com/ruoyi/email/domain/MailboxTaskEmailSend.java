package com.ruoyi.email.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发送邮件对象 mailbox_task_email_send
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public class MailboxTaskEmailSend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    @Excel(name = "邮箱任务ID")
    private Long taskId;

    /** 发件人 */
    @Excel(name = "发件人")
    private String fromer;

    /** 收件人JSON */
    @Excel(name = "收件人JSON")
    private String receiver;

    /** 密送人JSON,格式和收件人一样 */
    @Excel(name = "密送人JSON,格式和收件人一样")
    private String bcc;

    /** 抄送人JSON,格式和收件人一样 */
    @Excel(name = "抄送人JSON,格式和收件人一样")
    private String cc;

    /** 邮件主题 */
    @Excel(name = "邮件主题")
    private String title;

    /** 状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败 */
    @Excel(name = "状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败")
    private Integer status;

    /** 定时发送: 0.否 1.是 */
    @Excel(name = "定时发送: 0.否 1.是")
    private Integer delayedTxFlag;

    /** 收件人所在时区 */
    @Excel(name = "收件人所在时区")
    private String recipientTimeZone;

    /** 收件人当地时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收件人当地时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recipientLocalTime;

    /** 是否待处理: 0.否 1.是 */
    @Excel(name = "是否待处理: 0.否 1.是")
    private Integer pendingFlag;

    /** 待处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "待处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pendingTime;

    /** 是否固定: 0.否 1.是 */
    @Excel(name = "是否固定: 0.否 1.是")
    private Integer fixedFlag;

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
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setFromer(String fromer) 
    {
        this.fromer = fromer;
    }

    public String getFromer() 
    {
        return fromer;
    }
    public void setReceiver(String receiver) 
    {
        this.receiver = receiver;
    }

    public String getReceiver() 
    {
        return receiver;
    }
    public void setBcc(String bcc) 
    {
        this.bcc = bcc;
    }

    public String getBcc() 
    {
        return bcc;
    }
    public void setCc(String cc) 
    {
        this.cc = cc;
    }

    public String getCc() 
    {
        return cc;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDelayedTxFlag(Integer delayedTxFlag) 
    {
        this.delayedTxFlag = delayedTxFlag;
    }

    public Integer getDelayedTxFlag() 
    {
        return delayedTxFlag;
    }
    public void setRecipientTimeZone(String recipientTimeZone) 
    {
        this.recipientTimeZone = recipientTimeZone;
    }

    public String getRecipientTimeZone() 
    {
        return recipientTimeZone;
    }
    public void setRecipientLocalTime(Date recipientLocalTime) 
    {
        this.recipientLocalTime = recipientLocalTime;
    }

    public Date getRecipientLocalTime() 
    {
        return recipientLocalTime;
    }
    public void setPendingFlag(Integer pendingFlag) 
    {
        this.pendingFlag = pendingFlag;
    }

    public Integer getPendingFlag() 
    {
        return pendingFlag;
    }
    public void setPendingTime(Date pendingTime) 
    {
        this.pendingTime = pendingTime;
    }

    public Date getPendingTime() 
    {
        return pendingTime;
    }
    public void setFixedFlag(Integer fixedFlag) 
    {
        this.fixedFlag = fixedFlag;
    }

    public Integer getFixedFlag() 
    {
        return fixedFlag;
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
            .append("taskId", getTaskId())
            .append("fromer", getFromer())
            .append("receiver", getReceiver())
            .append("bcc", getBcc())
            .append("cc", getCc())
            .append("title", getTitle())
            .append("status", getStatus())
            .append("delayedTxFlag", getDelayedTxFlag())
            .append("recipientTimeZone", getRecipientTimeZone())
            .append("recipientLocalTime", getRecipientLocalTime())
            .append("pendingFlag", getPendingFlag())
            .append("pendingTime", getPendingTime())
            .append("fixedFlag", getFixedFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
