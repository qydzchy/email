package com.ruoyi.email.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 拉取邮件对象 mailbox_task_email_pull
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public class MailboxTaskEmailPull extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    @Excel(name = "邮箱任务ID")
    private Long taskId;

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

    /** 原始邮件存储路径 */
    @Excel(name = "原始邮件存储路径")
    private String emlPath;

    /** 是否已读: 0.否 1.是 */
    @Excel(name = "是否已读: 0.否 1.是")
    private Integer readFlag;

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

    /** 垃圾邮件: 0.否 1.是 */
    @Excel(name = "垃圾邮件: 0.否 1.是")
    private Integer spamFlag;

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
    public void setEmlPath(String emlPath) 
    {
        this.emlPath = emlPath;
    }

    public String getEmlPath() 
    {
        return emlPath;
    }
    public void setReadFlag(Integer readFlag) 
    {
        this.readFlag = readFlag;
    }

    public Integer getReadFlag() 
    {
        return readFlag;
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
    public void setSpamFlag(Integer spamFlag) 
    {
        this.spamFlag = spamFlag;
    }

    public Integer getSpamFlag() 
    {
        return spamFlag;
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
            .append("uid", getUid())
            .append("fromer", getFromer())
            .append("receiver", getReceiver())
            .append("bcc", getBcc())
            .append("cc", getCc())
            .append("folder", getFolder())
            .append("folderId", getFolderId())
            .append("title", getTitle())
            .append("emlPath", getEmlPath())
            .append("readFlag", getReadFlag())
            .append("pendingFlag", getPendingFlag())
            .append("pendingTime", getPendingTime())
            .append("fixedFlag", getFixedFlag())
            .append("spamFlag", getSpamFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
