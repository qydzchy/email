package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收发件规则对象 mailbox_transceiver_rule
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class TransceiverRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 规则类型: 1.收件规则 2.发件规则 */
    @Excel(name = "规则类型: 1.收件规则 2.发件规则")
    private Integer ruleType;

    /** 基本信息-规则名称 */
    @Excel(name = "基本信息-规则名称")
    private String ruleName;

    /** 执行条件: 1.执行以下操作 2.满足以下任一条件 */
    @Excel(name = "执行条件: 1.执行以下操作 2.满足以下任一条件")
    private Integer executeCondition;

    /** 执行条件内容 */
    @Excel(name = "执行条件内容")
    private String executeConditionContent;

    /** 执行操作: 1.执行以下操作 2.移动到【已删除邮件】 */
    @Excel(name = "执行操作: 1.执行以下操作 2.移动到【已删除邮件】")
    private Integer executeOperation;

    /** 执行操作内容 */
    @Excel(name = "执行操作内容")
    private String executeOperationContent;

    /** 应用于历史邮件: 0.否 1.是 */
    @Excel(name = "应用于历史邮件: 0.否 1.是")
    private Integer applyToHistoryMailFlag;

    /** 执行邮箱ID: 0.表示全部邮箱 */
    @Excel(name = "执行邮箱ID: 0.表示全部邮箱")
    private Long executeTaskId;

    /** 其他发件规则: 1.继续执行 2.不再执行 */
    @Excel(name = "其他发件规则: 1.继续执行 2.不再执行")
    private Integer otherSendingRules;

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
    public void setRuleType(Integer ruleType) 
    {
        this.ruleType = ruleType;
    }

    public Integer getRuleType() 
    {
        return ruleType;
    }
    public void setRuleName(String ruleName) 
    {
        this.ruleName = ruleName;
    }

    public String getRuleName() 
    {
        return ruleName;
    }
    public void setExecuteCondition(Integer executeCondition) 
    {
        this.executeCondition = executeCondition;
    }

    public Integer getExecuteCondition() 
    {
        return executeCondition;
    }
    public void setExecuteConditionContent(String executeConditionContent) 
    {
        this.executeConditionContent = executeConditionContent;
    }

    public String getExecuteConditionContent() 
    {
        return executeConditionContent;
    }
    public void setExecuteOperation(Integer executeOperation) 
    {
        this.executeOperation = executeOperation;
    }

    public Integer getExecuteOperation() 
    {
        return executeOperation;
    }
    public void setExecuteOperationContent(String executeOperationContent) 
    {
        this.executeOperationContent = executeOperationContent;
    }

    public String getExecuteOperationContent() 
    {
        return executeOperationContent;
    }
    public void setApplyToHistoryMailFlag(Integer applyToHistoryMailFlag) 
    {
        this.applyToHistoryMailFlag = applyToHistoryMailFlag;
    }

    public Integer getApplyToHistoryMailFlag() 
    {
        return applyToHistoryMailFlag;
    }
    public void setExecuteTaskId(Long executeTaskId) 
    {
        this.executeTaskId = executeTaskId;
    }

    public Long getExecuteTaskId() 
    {
        return executeTaskId;
    }
    public void setOtherSendingRules(Integer otherSendingRules) 
    {
        this.otherSendingRules = otherSendingRules;
    }

    public Integer getOtherSendingRules() 
    {
        return otherSendingRules;
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
            .append("ruleType", getRuleType())
            .append("ruleName", getRuleName())
            .append("executeCondition", getExecuteCondition())
            .append("executeConditionContent", getExecuteConditionContent())
            .append("executeOperation", getExecuteOperation())
            .append("executeOperationContent", getExecuteOperationContent())
            .append("applyToHistoryMailFlag", getApplyToHistoryMailFlag())
            .append("executeTaskId", getExecuteTaskId())
            .append("otherSendingRules", getOtherSendingRules())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
