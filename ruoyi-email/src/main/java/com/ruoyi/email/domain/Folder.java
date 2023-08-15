package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件夹对象 mailbox_folder
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class Folder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 父文件夹的ID,如果是顶级文件夹,此项为NULL */
    @Excel(name = "父文件夹的ID,如果是顶级文件夹,此项为NULL")
    private Long parentFolderId;

    /** 文件夹名称 */
    @Excel(name = "文件夹名称")
    private String name;

    /** 类型: 1.系统文件夹 2.自定义文件夹 */
    @Excel(name = "类型: 1.系统文件夹 2.自定义文件夹")
    private Integer type;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long orderNum;

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
    public void setParentFolderId(Long parentFolderId) 
    {
        this.parentFolderId = parentFolderId;
    }

    public Long getParentFolderId() 
    {
        return parentFolderId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
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
            .append("parentFolderId", getParentFolderId())
            .append("name", getName())
            .append("type", getType())
            .append("orderNum", getOrderNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
