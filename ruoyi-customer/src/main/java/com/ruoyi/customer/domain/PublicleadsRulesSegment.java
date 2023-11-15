package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公海规则客群对象 customer_publicleads_rules_segment
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
public class PublicleadsRulesSegment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 公海规则ID */
    @Excel(name = "公海规则ID")
    private Long publicleadsRulesId;

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
    public void setPublicleadsRulesId(Long publicleadsRulesId) 
    {
        this.publicleadsRulesId = publicleadsRulesId;
    }

    public Long getPublicleadsRulesId() 
    {
        return publicleadsRulesId;
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
            .append("publicleadsRulesId", getPublicleadsRulesId())
            .append("segmentId", getSegmentId())
            .toString();
    }
}
