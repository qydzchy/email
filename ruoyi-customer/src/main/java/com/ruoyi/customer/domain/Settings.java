package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户设置对象 customer_settings
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public class Settings extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 子分组必选 0.未选 1.已选 */
    @Excel(name = "子分组必选 0.未选 1.已选")
    private Integer mandatorySubgroupFlag;

    /** 提前标识 0.关闭 1.启动 */
    @Excel(name = "提前标识 0.关闭 1.启动")
    private Integer advanceFlag;

    /** 提前几天 */
    @Excel(name = "提前几天")
    private Long advanceDays;

    /** 排除禁用账号 0.未选 1.已选 */
    @Excel(name = "排除禁用账号 0.未选 1.已选")
    private Integer accountDisabledFlag;

    /** 领取限制 1.限制 2.不限制 */
    @Excel(name = "领取限制 1.限制 2.不限制")
    private Integer claimLimitFlag;

    /** 限制几天内，原跟进人不能领取同一个客户 */
    @Excel(name = "限制几天内，原跟进人不能领取同一个客户")
    private Long claimLimitDays;

    /** 商机查看规则
1.领取公海客户后，不能查看客户历史商机
2.领取公海客户后，可查看客户历史商机
 */
    @Excel(name = "商机查看规则")
    private Integer opportunityViewRule;

    /** 是否显示公海联系人 0.否 1.是 */
    @Excel(name = "是否显示公海联系人 0.否 1.是")
    private Integer showPublicLeadsFlag;

    /** 写跟进时间
1.提交跟进记录时默认提交时间，不允许修改
2.提交跟进记录时默认提交时间，可手动修改为历史时间
 */
    @Excel(name = "写跟进时间")
    private Integer followupTime;

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
    public void setMandatorySubgroupFlag(Integer mandatorySubgroupFlag) 
    {
        this.mandatorySubgroupFlag = mandatorySubgroupFlag;
    }

    public Integer getMandatorySubgroupFlag() 
    {
        return mandatorySubgroupFlag;
    }
    public void setAdvanceFlag(Integer advanceFlag) 
    {
        this.advanceFlag = advanceFlag;
    }

    public Integer getAdvanceFlag() 
    {
        return advanceFlag;
    }
    public void setAdvanceDays(Long advanceDays) 
    {
        this.advanceDays = advanceDays;
    }

    public Long getAdvanceDays() 
    {
        return advanceDays;
    }
    public void setAccountDisabledFlag(Integer accountDisabledFlag) 
    {
        this.accountDisabledFlag = accountDisabledFlag;
    }

    public Integer getAccountDisabledFlag() 
    {
        return accountDisabledFlag;
    }
    public void setClaimLimitFlag(Integer claimLimitFlag) 
    {
        this.claimLimitFlag = claimLimitFlag;
    }

    public Integer getClaimLimitFlag() 
    {
        return claimLimitFlag;
    }
    public void setClaimLimitDays(Long claimLimitDays) 
    {
        this.claimLimitDays = claimLimitDays;
    }

    public Long getClaimLimitDays() 
    {
        return claimLimitDays;
    }
    public void setOpportunityViewRule(Integer opportunityViewRule) 
    {
        this.opportunityViewRule = opportunityViewRule;
    }

    public Integer getOpportunityViewRule() 
    {
        return opportunityViewRule;
    }
    public void setShowPublicLeadsFlag(Integer showPublicLeadsFlag) 
    {
        this.showPublicLeadsFlag = showPublicLeadsFlag;
    }

    public Integer getShowPublicLeadsFlag() 
    {
        return showPublicLeadsFlag;
    }
    public void setFollowupTime(Integer followupTime) 
    {
        this.followupTime = followupTime;
    }

    public Integer getFollowupTime() 
    {
        return followupTime;
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
            .append("mandatorySubgroupFlag", getMandatorySubgroupFlag())
            .append("advanceFlag", getAdvanceFlag())
            .append("advanceDays", getAdvanceDays())
            .append("accountDisabledFlag", getAccountDisabledFlag())
            .append("claimLimitFlag", getClaimLimitFlag())
            .append("claimLimitDays", getClaimLimitDays())
            .append("opportunityViewRule", getOpportunityViewRule())
            .append("showPublicLeadsFlag", getShowPublicLeadsFlag())
            .append("followupTime", getFollowupTime())
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
