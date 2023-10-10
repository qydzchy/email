package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件标签对象 mailbox_task_email_label
 * 
 * @author tangJM
 * @date 2023-10-10
 */
public class TaskEmailLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮件ID */
    @Excel(name = "邮件ID")
    private Long emailId;

    /** 标签ID */
    @Excel(name = "标签ID")
    private Long labelId;

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
    public void setLabelId(Long labelId) 
    {
        this.labelId = labelId;
    }

    public Long getLabelId() 
    {
        return labelId;
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
            .append("labelId", getLabelId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
