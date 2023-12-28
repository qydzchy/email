package com.ruoyi.email.domain.vo;

import lombok.Data;

@Data
public class TransceiverRuleVO {
    /** 主键 */
    private Long id;

    /** 规则类型: 1.收件规则 2.发件规则 */
    private Integer ruleType;

    /** 规则名称 */
    private String ruleName;

    /** 执行条件: 1.执行以下操作 2.满足以下任一条件 */
    private Integer executeCondition;

    /** 执行条件内容 */
    private String executeConditionContent;

    /** 执行操作: 1.执行以下操作 2.移动到【已删除邮件】 */
    private Integer executeOperation;

    /** 是否固定: 0.否 1.是 */
    private Integer fixedFlag;

    /** 是否已读: 0.否 1.是 */
    private Integer readFlag;

    /** 文件夹ID,系统文件夹 */
    private Long folderId;

    /** 转发至 */
    private String forwardTo;

    /** 是否待处理: 0.否 1.是 */
    private Integer pendingFlag;

    /** 待处理类型 1.邮件接收时间 2.邮件接收时间之后的第 */
    private Integer pendingType;

    /** 待处理-天 */
    private Integer pendingDay;

    /** 待处理-时间 */
    private String pendingTime;

    /** 自动回复 */
    private String autoResponse;

    /** 应用于历史邮件: 0.否 1.是 */
    private Integer applyToHistoryMailFlag;

    /** 执行邮箱ID: 0.表示全部邮箱 */
    private Long executeTaskId;

    /** 其他发件规则: 1.继续执行 2.不再执行 */
    private Integer otherSendingRules;

    /** 状态 1.开启 0.关闭 */
    private Integer status;

    /** 顺序 */
    private Long order;
}
