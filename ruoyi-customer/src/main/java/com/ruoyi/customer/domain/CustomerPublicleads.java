package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 移入客户到公海对象 customer_customer_publicleads
 * 
 * @author tangJM.
 * @date 2023-11-04
 */
public class CustomerPublicleads extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 公海分组ID */
    @Excel(name = "公海分组ID")
    private Long publicleadsGroupsId;

    /** 移入公海原因ID */
    @Excel(name = "移入公海原因ID")
    private Long publicleadsReasonId;

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
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setPublicleadsGroupsId(Long publicleadsGroupsId) 
    {
        this.publicleadsGroupsId = publicleadsGroupsId;
    }

    public Long getPublicleadsGroupsId() 
    {
        return publicleadsGroupsId;
    }
    public void setPublicleadsReasonId(Long publicleadsReasonId) 
    {
        this.publicleadsReasonId = publicleadsReasonId;
    }

    public Long getPublicleadsReasonId() 
    {
        return publicleadsReasonId;
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
            .append("customerId", getCustomerId())
            .append("publicleadsGroupsId", getPublicleadsGroupsId())
            .append("publicleadsReasonId", getPublicleadsReasonId())
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
