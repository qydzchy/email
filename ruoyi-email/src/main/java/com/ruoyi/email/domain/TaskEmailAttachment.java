package com.ruoyi.email.domain;

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

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String name;

    /** 附件大小 */
    @Excel(name = "附件大小")
    private Long size;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setEmailId(Long emailId)
    {
        this.emailId = emailId;
    }

    public Long getEmailId() 
    {
        return emailId;
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
        return "TaskEmailAttachment{" +
                "id=" + id +
                ", emailId=" + emailId +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
