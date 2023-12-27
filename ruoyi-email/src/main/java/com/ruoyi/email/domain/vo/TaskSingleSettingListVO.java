package com.ruoyi.email.domain.vo;

import lombok.Data;

@Data
public class TaskSingleSettingListVO {

    private Long id;

    /** 任务ID */
    private Long taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /** 写信昵称 */
    private String letterNickname;

    /** 默认签名 */
    private Long defaultSignatureId;

    /** 回复/转发签名 */
    private Long replySignatureId;

    /** 默认抄送: 0.关闭 1.启用 */
    private Integer defaultCcFlag;

    /** 默认抄送,请使用";"分隔多个邮箱 */
    private String defaultCc;

    /** 默认密送: 0.关闭 1.启用 */
    private Integer defaultBccFlag;

    /** 默认密送,请使用";"分隔多个邮箱 */
    private String defaultBcc;

    /** 是否追踪邮件: 0.否 1.是 */
    private Integer traceFlag;

    /** 是否回执: 0.否 1.是 */
    private Integer returnReceiptFlag;
}
