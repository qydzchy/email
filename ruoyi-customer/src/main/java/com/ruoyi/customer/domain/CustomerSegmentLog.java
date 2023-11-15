package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户关联客群日志对象 customer_customer_segment_log
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
public class CustomerSegmentLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 客群ID */
    @Excel(name = "客群ID")
    private Long segmentId;

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
    public void setSegmentId(Long segmentId) 
    {
        this.segmentId = segmentId;
    }

    public Long getSegmentId() 
    {
        return segmentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("segmentId", getSegmentId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
