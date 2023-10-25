package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户分组对象 customer_packet
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public class Packet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 父ID（第一级为-1） */
    @Excel(name = "父ID", readConverterExp = "第=一级为-1")
    private Long parenId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 可用成员 1.全部成员 2.特定成员-部门 3.特定成员-业务员 */
    @Excel(name = "可用成员 1.全部成员 2.特定成员-部门 3.特定成员-业务员")
    private Integer availableMember;

    /**
     * 指定成员
     */
    private String designatedMember;

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
    public void setParenId(Long parenId) 
    {
        this.parenId = parenId;
    }

    public Long getParenId() 
    {
        return parenId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAvailableMember(Integer availableMember) 
    {
        this.availableMember = availableMember;
    }

    public Integer getAvailableMember() 
    {
        return availableMember;
    }

    public String getDesignatedMember() {
        return designatedMember;
    }

    public void setDesignatedMember(String designatedMember) {
        this.designatedMember = designatedMember;
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
            .append("parenId", getParenId())
            .append("name", getName())
            .append("availableMember", getAvailableMember())
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
