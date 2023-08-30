package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件附件对象 mailbox_task_email_attachment
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class TaskEmailAttachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮件ID */
    @Excel(name = "邮件ID")
    private Long emailId;

    /** 邮件类型 1.拉取 2.发送 */
    @Excel(name = "邮件类型 1.拉取 2.发送")
    private Integer type;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String name;

    /** 附件大小 */
    @Excel(name = "附件大小")
    private Integer size;

    /** 附件地址路径 */
    @Excel(name = "附件地址路径")
    private String path;

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
    public void setEmailId(Long emailId) 
    {
        this.emailId = emailId;
    }

    public Long getEmailId() 
    {
        return emailId;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
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
            .append("type", getType())
            .append("name", getName())
            .append("path", getPath())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
