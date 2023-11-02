package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户来源关联对象 customer_customer_source
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public class CustomerSource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 客户来源ID */
    @Excel(name = "客户来源ID")
    private Long sourceId;

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
    public void setSourceId(Long sourceId) 
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId() 
    {
        return sourceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("sourceId", getSourceId())
            .toString();
    }
}
