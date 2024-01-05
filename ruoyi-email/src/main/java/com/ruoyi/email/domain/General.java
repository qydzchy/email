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
 * @author tangJM
 * @date 2023-12-27
 */
public class General extends BaseEntity
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
    private String fontColor;

    /** 群发箱视图​: 1.任务视图 2.邮件视图 */
    @Excel(name = "群发箱视图​: 1.任务视图 2.邮件视图")
    private Integer groupBoxView;

    /** 群发邮件展示 1.仅在群发箱 2.发件箱与群发箱 */
    @Excel(name = "群发邮件展示 1.仅在群发箱 2.发件箱与群发箱")
    private Integer massEmailDisplay;

    /** 提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒 */
    @Excel(name = "提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒")
    private String remind;

    /** 签名ID */
    @Excel(name = "签名ID")
    private Long signatureId;

    /** 写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效) 3.发送后返回上一页 */
    @Excel(name = "写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效) 3.发送后返回上一页")
    private String writeLetter;

    /** 粘贴格式 1,始终移除格式 2.始终保持格式 3.每次粘贴提醒 */
    @Excel(name = "粘贴格式 1,始终移除格式 2.始终保持格式 3.每次粘贴提醒")
    private Integer pasteFormat;

    /** 自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次) */
    @Excel(name = "自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次)")
    private Integer autoResponseFlag;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 最后一天标识 1.勾选 0未选 */
    @Excel(name = "最后一天标识 1.勾选 0未选")
    private Integer lastDayFlag;

    /** 最后一天 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后一天", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastDay;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String reContent;

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
    public void setFontColor(String fontColor)
    {
        this.fontColor = fontColor;
    }

    public String getFontColor()
    {
        return fontColor;
    }
    public void setGroupBoxView(Integer groupBoxView)
    {
        this.groupBoxView = groupBoxView;
    }

    public Integer getGroupBoxView()
    {
        return groupBoxView;
    }
    public void setMassEmailDisplay(Integer massEmailDisplay)
    {
        this.massEmailDisplay = massEmailDisplay;
    }

    public Integer getMassEmailDisplay()
    {
        return massEmailDisplay;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
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
    public void setPasteFormat(Integer pasteFormat)
    {
        this.pasteFormat = pasteFormat;
    }

    public Integer getPasteFormat()
    {
        return pasteFormat;
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
    public void setLastDayFlag(Integer lastDayFlag)
    {
        this.lastDayFlag = lastDayFlag;
    }

    public Integer getLastDayFlag()
    {
        return lastDayFlag;
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
                .append("defaultTaskId", getDefaultTaskId())
                .append("maxPerPage", getMaxPerPage())
                .append("emailReadingModeFlag", getEmailReadingModeFlag())
                .append("moveDeleteReport", getMoveDeleteReport())
                .append("language", getLanguage())
                .append("defaultFont", getDefaultFont())
                .append("fontSize", getFontSize())
                .append("fontColor", getFontColor())
                .append("groupBoxView", getGroupBoxView())
                .append("massEmailDisplay", getMassEmailDisplay())
                .append("remind", getRemind())
                .append("signatureId", getSignatureId())
                .append("writeLetter", getWriteLetter())
                .append("pasteFormat", getPasteFormat())
                .append("autoResponseFlag", getAutoResponseFlag())
                .append("startTime", getStartTime())
                .append("lastDayFlag", getLastDayFlag())
                .append("lastDay", getLastDay())
                .append("reContent", getReContent())
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
