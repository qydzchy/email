package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领取上限对象 customer_publicleads_claim_limit
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public class PublicleadsClaimLimit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 人员ID */
    @Excel(name = "人员ID")
    private Long userId;

    /** 领取上限 */
    @Excel(name = "领取上限")
    private Integer claimLimit;

    /** 上限周期 1.每日2.每周3.每月4.每年 */
    @Excel(name = "上限周期 1.每日2.每周3.每月4.每年")
    private Integer claimPeriod;

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setClaimLimit(Integer claimLimit)
    {
        this.claimLimit = claimLimit;
    }

    public Integer getClaimLimit()
    {
        return claimLimit;
    }
    public void setClaimPeriod(Integer claimPeriod) 
    {
        this.claimPeriod = claimPeriod;
    }

    public Integer getClaimPeriod() 
    {
        return claimPeriod;
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
            .append("userId", getUserId())
            .append("claimLimit", getClaimLimit())
            .append("claimPeriod", getClaimPeriod())
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
