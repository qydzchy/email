package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单个邮箱设置对象 mailbox_task_single_setting
 *
 * @author tangJM.
 * @date 2023-12-27
 */
public class TaskSingleSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 写信昵称 */
    @Excel(name = "写信昵称")
    private String letterNickname;

    /** 默认签名 */
    @Excel(name = "默认签名")
    private Long defaultSignatureId;

    /** 回复/转发签名 */
    @Excel(name = "回复/转发签名")
    private Long replySignatureId;

    /** 默认抄送: 0.关闭 1.启用 */
    @Excel(name = "默认抄送: 0.关闭 1.启用")
    private Integer defaultCcFlag;

    /** 默认抄送,请使用";"分隔多个邮箱 */
    @Excel(name = "默认抄送,请使用';'分隔多个邮箱")
    private String defaultCc;

    /** 默认密送: 0.关闭 1.启用 */
    @Excel(name = "默认密送: 0.关闭 1.启用")
    private Integer defaultBccFlag;

    /** 默认密送,请使用";"分隔多个邮箱 */
    @Excel(name = "默认密送,请使用';'分隔多个邮箱")
    private String defaultBcc;

    /** 是否追踪邮件: 0.否 1.是 */
    @Excel(name = "是否追踪邮件: 0.否 1.是")
    private Integer traceFlag;

    /** 是否回执: 0.否 1.是 */
    @Excel(name = "是否回执: 0.否 1.是")
    private Integer returnReceiptFlag;

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
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setLetterNickname(String letterNickname)
    {
        this.letterNickname = letterNickname;
    }

    public String getLetterNickname()
    {
        return letterNickname;
    }
    public void setDefaultSignatureId(Long defaultSignatureId)
    {
        this.defaultSignatureId = defaultSignatureId;
    }

    public Long getDefaultSignatureId()
    {
        return defaultSignatureId;
    }
    public void setReplySignatureId(Long replySignatureId)
    {
        this.replySignatureId = replySignatureId;
    }

    public Long getReplySignatureId()
    {
        return replySignatureId;
    }
    public void setDefaultCcFlag(Integer defaultCcFlag)
    {
        this.defaultCcFlag = defaultCcFlag;
    }

    public Integer getDefaultCcFlag()
    {
        return defaultCcFlag;
    }
    public void setDefaultCc(String defaultCc)
    {
        this.defaultCc = defaultCc;
    }

    public String getDefaultCc()
    {
        return defaultCc;
    }
    public void setDefaultBccFlag(Integer defaultBccFlag)
    {
        this.defaultBccFlag = defaultBccFlag;
    }

    public Integer getDefaultBccFlag()
    {
        return defaultBccFlag;
    }
    public void setDefaultBcc(String defaultBcc)
    {
        this.defaultBcc = defaultBcc;
    }

    public String getDefaultBcc()
    {
        return defaultBcc;
    }
    public void setTraceFlag(Integer traceFlag)
    {
        this.traceFlag = traceFlag;
    }

    public Integer getTraceFlag()
    {
        return traceFlag;
    }
    public void setReturnReceiptFlag(Integer returnReceiptFlag)
    {
        this.returnReceiptFlag = returnReceiptFlag;
    }

    public Integer getReturnReceiptFlag()
    {
        return returnReceiptFlag;
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
                .append("taskId", getTaskId())
                .append("letterNickname", getLetterNickname())
                .append("defaultSignatureId", getDefaultSignatureId())
                .append("replySignatureId", getReplySignatureId())
                .append("defaultCcFlag", getDefaultCcFlag())
                .append("defaultCc", getDefaultCc())
                .append("defaultBccFlag", getDefaultBccFlag())
                .append("defaultBcc", getDefaultBcc())
                .append("traceFlag", getTraceFlag())
                .append("returnReceiptFlag", getReturnReceiptFlag())
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
