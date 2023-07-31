package com.ruoyi.email.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮箱常规对象 mailbox_general
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public class MailboxGeneral extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 默认邮箱 */
    @Excel(name = "默认邮箱")
    private Long defaultTaskId;

    /** 每页显示邮件数量 */
    @Excel(name = "每页显示邮件数量")
    private Integer maxPerPage;

    /** 邮件阅读模式: 1.启用(适合大屏用户,可快速切换邮件) 0.停用(适合小屏用户,更多空间查看邮件详情) 启用阅读模式后,查看邮件详情时,将保持左侧列表展示 */
    @Excel(name = "邮件阅读模式: 1.启用(适合大屏用户,可快速切换邮件) 0.停用(适合小屏用户,更多空间查看邮件详情) 启用阅读模式后,查看邮件详情时,将保持左侧列表展示")
    private Integer emailReadingModeFlag;

    /** 移动/删除/举报后: 1.阅读下一封邮件(推荐) 2.回到当前邮件列表 */
    @Excel(name = "移动/删除/举报后: 1.阅读下一封邮件(推荐) 2.回到当前邮件列表")
    private Integer moveDeleteReport;

    /** 语言: 1.简体中文 2.繁体中文 3.英文 */
    @Excel(name = "语言: 1.简体中文 2.繁体中文 3.英文")
    private Integer language;

    /** 默认字体 */
    @Excel(name = "默认字体")
    private String defaultFont;

    /** 文字大小 */
    @Excel(name = "文字大小")
    private String fontSize;

    /** 文字颜色 */
    @Excel(name = "文字颜色")
    private String wordColor;

    /** 群发箱视图​: 1.任务视图 2.邮件视图 */
    @Excel(name = "群发箱视图​: 1.任务视图 2.邮件视图")
    private Integer groupBoxView;

    /** 提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒 */
    @Excel(name = "提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒")
    private Integer remind;

    /** 签名ID */
    @Excel(name = "签名ID")
    private Long signatureId;

    /** 写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效) */
    @Excel(name = "写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效)")
    private String writeLetter;

    /** 自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次) */
    @Excel(name = "自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次)")
    private Integer autoResponseFlag;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 最后一天 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一天", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastDay;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String reContent;

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
    public void setDefaultTaskId(Long defaultTaskId) 
    {
        this.defaultTaskId = defaultTaskId;
    }

    public Long getDefaultTaskId() 
    {
        return defaultTaskId;
    }
    public void setMaxPerPage(Integer maxPerPage) 
    {
        this.maxPerPage = maxPerPage;
    }

    public Integer getMaxPerPage() 
    {
        return maxPerPage;
    }
    public void setEmailReadingModeFlag(Integer emailReadingModeFlag) 
    {
        this.emailReadingModeFlag = emailReadingModeFlag;
    }

    public Integer getEmailReadingModeFlag() 
    {
        return emailReadingModeFlag;
    }
    public void setMoveDeleteReport(Integer moveDeleteReport) 
    {
        this.moveDeleteReport = moveDeleteReport;
    }

    public Integer getMoveDeleteReport() 
    {
        return moveDeleteReport;
    }
    public void setLanguage(Integer language) 
    {
        this.language = language;
    }

    public Integer getLanguage() 
    {
        return language;
    }
    public void setDefaultFont(String defaultFont) 
    {
        this.defaultFont = defaultFont;
    }

    public String getDefaultFont() 
    {
        return defaultFont;
    }
    public void setFontSize(String fontSize) 
    {
        this.fontSize = fontSize;
    }

    public String getFontSize() 
    {
        return fontSize;
    }
    public void setWordColor(String wordColor) 
    {
        this.wordColor = wordColor;
    }

    public String getWordColor() 
    {
        return wordColor;
    }
    public void setGroupBoxView(Integer groupBoxView) 
    {
        this.groupBoxView = groupBoxView;
    }

    public Integer getGroupBoxView() 
    {
        return groupBoxView;
    }
    public void setRemind(Integer remind) 
    {
        this.remind = remind;
    }

    public Integer getRemind() 
    {
        return remind;
    }
    public void setSignatureId(Long signatureId) 
    {
        this.signatureId = signatureId;
    }

    public Long getSignatureId() 
    {
        return signatureId;
    }
    public void setWriteLetter(String writeLetter) 
    {
        this.writeLetter = writeLetter;
    }

    public String getWriteLetter() 
    {
        return writeLetter;
    }
    public void setAutoResponseFlag(Integer autoResponseFlag) 
    {
        this.autoResponseFlag = autoResponseFlag;
    }

    public Integer getAutoResponseFlag() 
    {
        return autoResponseFlag;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setLastDay(Date lastDay) 
    {
        this.lastDay = lastDay;
    }

    public Date getLastDay() 
    {
        return lastDay;
    }
    public void setReContent(String reContent) 
    {
        this.reContent = reContent;
    }

    public String getReContent() 
    {
        return reContent;
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
            .append("defaultTaskId", getDefaultTaskId())
            .append("maxPerPage", getMaxPerPage())
            .append("emailReadingModeFlag", getEmailReadingModeFlag())
            .append("moveDeleteReport", getMoveDeleteReport())
            .append("language", getLanguage())
            .append("defaultFont", getDefaultFont())
            .append("fontSize", getFontSize())
            .append("wordColor", getWordColor())
            .append("groupBoxView", getGroupBoxView())
            .append("remind", getRemind())
            .append("signatureId", getSignatureId())
            .append("writeLetter", getWriteLetter())
            .append("autoResponseFlag", getAutoResponseFlag())
            .append("startTime", getStartTime())
            .append("lastDay", getLastDay())
            .append("reContent", getReContent())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
