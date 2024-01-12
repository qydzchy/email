package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收发件规则对象 mailbox_transceiver_rule
 *
 * @author tangJM
 * @date 2024-01-05
 */
public class TransceiverRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 规则类型: 1.收件规则 2.发件规则 */
    @Excel(name = "规则类型: 1.收件规则 2.发件规则")
    private Integer ruleType;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String ruleName;

    /** 执行条件: 1.满足以下所有条件 2.满足以下任一条件 */
    @Excel(name = "执行条件: 1.满足以下所有条件 2.满足以下任一条件")
    private Integer executeCondition;

    /** 执行条件内容 */
    @Excel(name = "执行条件内容")
    private String executeConditionContent;

    /** 执行操作: 1.执行以下操作 2.移动到【已删除邮件】 */
    @Excel(name = "执行操作: 1.执行以下操作 2.移动到【已删除邮件】")
    private Integer executeOperation;

    /** 是否固定: 0.否 1.是 */
    @Excel(name = "是否固定: 0.否 1.是")
    private Boolean fixedFlag;

    /** 是否已读: 0.否 1.是 */
    @Excel(name = "是否已读: 0.否 1.是")
    private Boolean readFlag;

    /** 是否分类: 0.否 1.是 */
    @Excel(name = "是否分类: 0.否 1.是")
    private Boolean labelFlag;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long labelId;

    /** 是否移动至：0.否 1.是 */
    @Excel(name = "是否移动至：0.否 1.是")
    private Boolean folderFlag;

    /** 文件夹ID */
    @Excel(name = "文件夹ID")
    private Long folderId;

    /** 是否转发至：0.否 1.是 */
    @Excel(name = "是否转发至：0.否 1.是")
    private Boolean forwardToFlag;

    /** 转发至 */
    @Excel(name = "转发至")
    private String forwardTo;

    /** 是否待处理: 0.否 1.是 */
    @Excel(name = "是否待处理: 0.否 1.是")
    private Boolean pendingFlag;

    /** 待处理类型 1.邮件接收时间 2.邮件接收时间之后的第 */
    @Excel(name = "待处理类型 1.邮件接收时间 2.邮件接收时间之后的第")
    private Integer pendingType;

    /** 待处理-天 */
    @Excel(name = "待处理-天")
    private Integer pendingDay;

    /** 待处理-时间 */
    @Excel(name = "待处理-时间")
    private String pendingTime;

    /** 是否自动回复：0.否 1.是 */
    @Excel(name = "是否自动回复：0.否 1.是")
    private Boolean autoResponseFlag;

    /** 自动回复 */
    @Excel(name = "自动回复")
    private String autoResponse;

    /** 应用于历史邮件: 0.否 1.是 */
    @Excel(name = "应用于历史邮件: 0.否 1.是")
    private Boolean applyToHistoryMailFlag;

    /** 应用于历史邮件类型：1.针对收件箱的历史邮件 2.针对收件箱及所有文件夹的历史邮件（不包括已删除） */
    @Excel(name = "应用于历史邮件类型：1.针对收件箱的历史邮件 2.针对收件箱及所有文件夹的历史邮件", readConverterExp = "不=包括已删除")
    private Integer applyToHistoryMailTrueType;

    /** 执行邮箱ID: 0.表示全部邮箱 */
    @Excel(name = "执行邮箱ID: 0.表示全部邮箱")
    private Long executeTaskId;

    /** 其他发件规则: 1.继续执行 2.不再执行 */
    @Excel(name = "其他发件规则: 1.继续执行 2.不再执行")
    private Integer otherSendingRules;

    /** 状态 1.开启 0.关闭 */
    @Excel(name = "状态 1.开启 0.关闭")
    private Integer status;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long orderNum;

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

    public void setLabelId(Long labelId)
    {
        this.labelId = labelId;
    }

    public Long getLabelId()
    {
        return labelId;
    }

    public void setFolderId(Long folderId)
    {
        this.folderId = folderId;
    }

    public Long getFolderId()
    {
        return folderId;
    }

    public Boolean getFixedFlag() {
        return fixedFlag;
    }

    public void setFixedFlag(Boolean fixedFlag) {
        this.fixedFlag = fixedFlag;
    }

    public Boolean getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Boolean readFlag) {
        this.readFlag = readFlag;
    }

    public Boolean getLabelFlag() {
        return labelFlag;
    }

    public void setLabelFlag(Boolean labelFlag) {
        this.labelFlag = labelFlag;
    }

    public Boolean getFolderFlag() {
        return folderFlag;
    }

    public void setFolderFlag(Boolean folderFlag) {
        this.folderFlag = folderFlag;
    }

    public Boolean getForwardToFlag() {
        return forwardToFlag;
    }

    public void setForwardToFlag(Boolean forwardToFlag) {
        this.forwardToFlag = forwardToFlag;
    }

    public void setPendingFlag(Boolean pendingFlag) {
        this.pendingFlag = pendingFlag;
    }

    public void setApplyToHistoryMailFlag(Boolean applyToHistoryMailFlag) {
        this.applyToHistoryMailFlag = applyToHistoryMailFlag;
    }

    public void setForwardTo(String forwardTo)
    {
        this.forwardTo = forwardTo;
    }

    public String getForwardTo()
    {
        return forwardTo;
    }

    public void setPendingType(Integer pendingType)
    {
        this.pendingType = pendingType;
    }

    public Integer getPendingType()
    {
        return pendingType;
    }
    public void setPendingDay(Integer pendingDay)
    {
        this.pendingDay = pendingDay;
    }

    public Integer getPendingDay()
    {
        return pendingDay;
    }
    public void setPendingTime(String pendingTime)
    {
        this.pendingTime = pendingTime;
    }

    public String getPendingTime()
    {
        return pendingTime;
    }

    public Boolean getAutoResponseFlag() {
        return autoResponseFlag;
    }

    public void setAutoResponseFlag(Boolean autoResponseFlag) {
        this.autoResponseFlag = autoResponseFlag;
    }

    public void setAutoResponse(String autoResponse)
    {
        this.autoResponse = autoResponse;
    }

    public String getAutoResponse()
    {
        return autoResponse;
    }

    public void setApplyToHistoryMailTrueType(Integer applyToHistoryMailTrueType)
    {
        this.applyToHistoryMailTrueType = applyToHistoryMailTrueType;
    }

    public Integer getApplyToHistoryMailTrueType()
    {
        return applyToHistoryMailTrueType;
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
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setOrderNum(Long orderNum)
    {
        this.orderNum = orderNum;
    }

    public Boolean getPendingFlag() {
        return pendingFlag;
    }

    public Boolean getApplyToHistoryMailFlag() {
        return applyToHistoryMailFlag;
    }

    public Long getOrderNum()
    {
        return orderNum;
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
                .append("ruleType", getRuleType())
                .append("ruleName", getRuleName())
                .append("executeCondition", getExecuteCondition())
                .append("executeConditionContent", getExecuteConditionContent())
                .append("executeOperation", getExecuteOperation())
                .append("fixedFlag", getFixedFlag())
                .append("readFlag", getReadFlag())
                .append("labelFlag", getLabelFlag())
                .append("labelId", getLabelId())
                .append("folderFlag", getFolderFlag())
                .append("folderId", getFolderId())
                .append("forwardToFlag", getForwardToFlag())
                .append("forwardTo", getForwardTo())
                .append("pendingFlag", getPendingFlag())
                .append("pendingType", getPendingType())
                .append("pendingDay", getPendingDay())
                .append("pendingTime", getPendingTime())
                .append("autoResponseFlag", getAutoResponseFlag())
                .append("autoResponse", getAutoResponse())
                .append("applyToHistoryMailFlag", getApplyToHistoryMailFlag())
                .append("applyToHistoryMailTrueType", getApplyToHistoryMailTrueType())
                .append("executeTaskId", getExecuteTaskId())
                .append("otherSendingRules", getOtherSendingRules())
                .append("status", getStatus())
                .append("orderNum", getOrderNum())
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
