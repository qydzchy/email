package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 其他配置对象 mailbox_other_config
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class OtherConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送 */
    @Excel(name = "邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送")
    private Integer delayedMailDelivery;

    /** 发送间隔 */
    @Excel(name = "发送间隔")
    private String sendingInterval;

    /** 当地实时时间: 0.停用 1.启用 */
    @Excel(name = "当地实时时间: 0.停用 1.启用")
    private Integer localRealTimeTime;

    /** 邮件发送提醒: 1.弹窗提醒 2.不提醒 */
    @Excel(name = "邮件发送提醒: 1.弹窗提醒 2.不提醒")
    private Integer emailRemindersFlag;

    /** 邮件翻译功能: 0.停用 1.启用 */
    @Excel(name = "邮件翻译功能: 0.停用 1.启用")
    private Integer emailTranslationFunctionFlag;

    /** 待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态 */
    @Excel(name = "待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态")
    private Integer pendingClose;

    /** 自动化待处理: 0.停用 1.启用 */
    @Excel(name = "自动化待处理: 0.停用 1.启用")
    private Integer automationPending;

    /** 异常邮箱检测: 0.停用 1.启用 */
    @Excel(name = "异常邮箱检测: 0.停用 1.启用")
    private Integer abnormalMailboxDetection;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDelayedMailDelivery(Integer delayedMailDelivery) 
    {
        this.delayedMailDelivery = delayedMailDelivery;
    }

    public Integer getDelayedMailDelivery() 
    {
        return delayedMailDelivery;
    }
    public void setSendingInterval(String sendingInterval) 
    {
        this.sendingInterval = sendingInterval;
    }

    public String getSendingInterval() 
    {
        return sendingInterval;
    }
    public void setLocalRealTimeTime(Integer localRealTimeTime) 
    {
        this.localRealTimeTime = localRealTimeTime;
    }

    public Integer getLocalRealTimeTime() 
    {
        return localRealTimeTime;
    }
    public void setEmailRemindersFlag(Integer emailRemindersFlag) 
    {
        this.emailRemindersFlag = emailRemindersFlag;
    }

    public Integer getEmailRemindersFlag() 
    {
        return emailRemindersFlag;
    }
    public void setEmailTranslationFunctionFlag(Integer emailTranslationFunctionFlag) 
    {
        this.emailTranslationFunctionFlag = emailTranslationFunctionFlag;
    }

    public Integer getEmailTranslationFunctionFlag() 
    {
        return emailTranslationFunctionFlag;
    }
    public void setPendingClose(Integer pendingClose) 
    {
        this.pendingClose = pendingClose;
    }

    public Integer getPendingClose() 
    {
        return pendingClose;
    }
    public void setAutomationPending(Integer automationPending) 
    {
        this.automationPending = automationPending;
    }

    public Integer getAutomationPending() 
    {
        return automationPending;
    }
    public void setAbnormalMailboxDetection(Integer abnormalMailboxDetection) 
    {
        this.abnormalMailboxDetection = abnormalMailboxDetection;
    }

    public Integer getAbnormalMailboxDetection() 
    {
        return abnormalMailboxDetection;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("delayedMailDelivery", getDelayedMailDelivery())
            .append("sendingInterval", getSendingInterval())
            .append("localRealTimeTime", getLocalRealTimeTime())
            .append("emailRemindersFlag", getEmailRemindersFlag())
            .append("emailTranslationFunctionFlag", getEmailTranslationFunctionFlag())
            .append("pendingClose", getPendingClose())
            .append("automationPending", getAutomationPending())
            .append("abnormalMailboxDetection", getAbnormalMailboxDetection())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
