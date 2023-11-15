package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户私海/公海日志对象 customer_customer_sea_log
 *
 * @author tangJM.
 * @date 2023-11-15
 */
public class CustomerSeaLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 私海/公海类型 1.私海 2.公海 */
    @Excel(name = "私海/公海类型 1.私海 2.公海")
    private Integer seaType;

    /** 类型 1.手动 2.自动 */
    @Excel(name = "类型 1.手动 2.自动")
    private Integer type;

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
    public void setSeaType(Integer seaType)
    {
        this.seaType = seaType;
    }

    public Integer getSeaType()
    {
        return seaType;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("customerId", getCustomerId())
                .append("seaType", getSeaType())
                .append("createTime", getCreateTime())
                .append("type", getType())
                .toString();
    }
}
