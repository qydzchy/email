package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户标签对象 customer_tag
 * 
 * @author tangJM.
 * @date 2023-11-17
 */
public class Tag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String name;

    /** 标签颜色 */
    @Excel(name = "标签颜色")
    private String color;

    /** 类型 1.公司 2.个人 */
    @Excel(name = "类型 1.公司 2.个人")
    private Integer type;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
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
            .append("name", getName())
            .append("color", getColor())
            .append("type", getType())
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
