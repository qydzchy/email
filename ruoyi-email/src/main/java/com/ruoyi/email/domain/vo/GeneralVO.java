package com.ruoyi.email.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GeneralVO {

    /** 主键 */
    private Long id;

    /** 默认邮箱 */
    private Long defaultTaskId;

    /** 每页显示邮件数量 */
    private Integer maxPerPage;

    /** 邮件阅读模式: 1.启用(适合大屏用户,可快速切换邮件) 0.停用(适合小屏用户,更多空间查看邮件详情) 启用阅读模式后,查看邮件详情时,将保持左侧列表展示 */
    private Integer emailReadingModeFlag;

    /** 移动/删除/举报后: 1.阅读下一封邮件(推荐) 2.回到当前邮件列表 */
    private Integer moveDeleteReport;

    /** 语言: 1.简体中文 2.繁体中文 3.英文 */
    private Integer language;

    /** 默认字体 */
    private String defaultFont;

    /** 文字大小 */
    private String fontSize;

    /** 文字颜色 */
    private String fontColor;

    /** 群发箱视图​: 1.任务视图 2.邮件视图 */
    private Integer groupBoxView;

    /** 群发邮件展示 1.仅在群发箱 2.发件箱与群发箱 */
    private Integer massEmailDisplay;

    /** 提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒 */
    private Integer remind;

    /** 签名ID */
    private Long signatureId;

    /** 写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效) 3.发送后返回上一页 */
    private String writeLetter;

    /** 粘贴格式 1,始终移除格式 2.始终保持格式 3.每次粘贴提醒 */
    private Integer pasteFormat;

    /** 自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次) */
    private Integer autoResponseFlag;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 最后一天标识 1.勾选 0未选 */
    private Integer lastDayFlag;

    /** 最后一天 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastDay;

    /** 回复内容 */
    private String reContent;
}
