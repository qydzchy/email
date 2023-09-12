package com.ruoyi.email.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件对象 mailbox_task_email
 * 
 * @author tangJM.
 * @date 2023-09-12
 */
public class TaskEmail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    @Excel(name = "邮箱任务ID")
    private Long taskId;

    /** 类型 1.收件 2.发件 */
    @Excel(name = "类型 1.收件 2.发件")
    private Integer type;

    /** 邮件唯一ID */
    @Excel(name = "邮件唯一ID")
    private String uid;

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

    /** 所属文件夹,对应邮箱平台文件夹 */
    @Excel(name = "所属文件夹,对应邮箱平台文件夹")
    private String folder;

    /** 文件夹ID,系统文件夹 */
    @Excel(name = "文件夹ID,系统文件夹")
    private Long folderId;

    /** 邮件主题 */
    @Excel(name = "邮件主题")
    private String title;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendDate;

    /** 原始邮件存储路径 */
    @Excel(name = "原始邮件存储路径")
    private String emlPath;

    /** 状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败 */
    @Excel(name = "状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败")
    private Integer status;

    /** 是否追踪 0.否 1.是 */
    @Excel(name = "是否追踪 0.否 1.是")
    private Boolean traceFlag;

    /** 定时发送: 0.否 1.是 */
    @Excel(name = "定时发送: 0.否 1.是")
    private Boolean delayedTxFlag;

    /** 收件人所在时区 */
    @Excel(name = "收件人所在时区")
    private String recipientTimeZone;

    /** 收件人当地时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收件人当地时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recipientLocalTime;

    /** 是否已读: 0.否 1.是 */
    @Excel(name = "是否已读: 0.否 1.是")
    private Boolean readFlag;

    /** 是否待处理: 0.否 1.是 */
    @Excel(name = "是否待处理: 0.否 1.是")
    private Boolean pendingFlag;

    /** 待处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "待处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pendingTime;

    /** 是否固定: 0.否 1.是 */
    @Excel(name = "是否固定: 0.否 1.是")
    private Boolean fixedFlag;

    /** 垃圾邮件: 0.否 1.是 */
    @Excel(name = "垃圾邮件: 0.否 1.是")
    private Boolean spamFlag;

    /** 每封邮件都应该有一个唯一的 Message-ID */
    @Excel(name = "每封邮件都应该有一个唯一的 Message-ID")
    private String messageId;

    /** 当你回复一封邮件时，你应该设置 In-Reply-To 头为原始邮件的 Message-ID。 */
    @Excel(name = "当你回复一封邮件时，你应该设置 In-Reply-To 头为原始邮件的 Message-ID。")
    private String inReplyTo;

    /** 邮件链的列表 */
    @Excel(name = "邮件链的列表")
    private String references;

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
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
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
    public void setFolder(String folder) 
    {
        this.folder = folder;
    }

    public String getFolder() 
    {
        return folder;
    }
    public void setFolderId(Long folderId) 
    {
        this.folderId = folderId;
    }

    public Long getFolderId() 
    {
        return folderId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setSendDate(Date sendDate) 
    {
        this.sendDate = sendDate;
    }

    public Date getSendDate() 
    {
        return sendDate;
    }
    public void setEmlPath(String emlPath) 
    {
        this.emlPath = emlPath;
    }

    public String getEmlPath() 
    {
        return emlPath;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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

    public Boolean getTraceFlag() {
        return traceFlag;
    }

    public void setTraceFlag(Boolean traceFlag) {
        this.traceFlag = traceFlag;
    }

    public Boolean getDelayedTxFlag() {
        return delayedTxFlag;
    }

    public void setDelayedTxFlag(Boolean delayedTxFlag) {
        this.delayedTxFlag = delayedTxFlag;
    }

    public Boolean getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Boolean readFlag) {
        this.readFlag = readFlag;
    }

    public Boolean getPendingFlag() {
        return pendingFlag;
    }

    public void setPendingFlag(Boolean pendingFlag) {
        this.pendingFlag = pendingFlag;
    }

    public Date getPendingTime() {
        return pendingTime;
    }

    public void setPendingTime(Date pendingTime) {
        this.pendingTime = pendingTime;
    }

    public Boolean getFixedFlag() {
        return fixedFlag;
    }

    public void setFixedFlag(Boolean fixedFlag) {
        this.fixedFlag = fixedFlag;
    }

    public Boolean getSpamFlag() {
        return spamFlag;
    }

    public void setSpamFlag(Boolean spamFlag) {
        this.spamFlag = spamFlag;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessageId() 
    {
        return messageId;
    }
    public void setInReplyTo(String inReplyTo) 
    {
        this.inReplyTo = inReplyTo;
    }

    public String getInReplyTo() 
    {
        return inReplyTo;
    }
    public void setReferences(String references) 
    {
        this.references = references;
    }

    public String getReferences() 
    {
        return references;
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
            .append("type", getType())
            .append("uid", getUid())
            .append("fromer", getFromer())
            .append("receiver", getReceiver())
            .append("bcc", getBcc())
            .append("cc", getCc())
            .append("folder", getFolder())
            .append("folderId", getFolderId())
            .append("title", getTitle())
            .append("sendDate", getSendDate())
            .append("emlPath", getEmlPath())
            .append("status", getStatus())
            .append("traceFlag", getTraceFlag())
            .append("delayedTxFlag", getDelayedTxFlag())
            .append("recipientTimeZone", getRecipientTimeZone())
            .append("recipientLocalTime", getRecipientLocalTime())
            .append("readFlag", getReadFlag())
            .append("pendingFlag", getPendingFlag())
            .append("pendingTime", getPendingTime())
            .append("fixedFlag", getFixedFlag())
            .append("spamFlag", getSpamFlag())
            .append("messageId", getMessageId())
            .append("inReplyTo", getInReplyTo())
            .append("references", getReferences())
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
