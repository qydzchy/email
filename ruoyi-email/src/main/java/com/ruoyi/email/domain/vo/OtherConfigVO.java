package com.ruoyi.email.domain.vo;

import lombok.Data;

@Data
public class OtherConfigVO {

    /** 主键 */
    private Long id;

    /** 邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送 */
    private Integer delayedMailDelivery;

    /** 发送间隔 */
    private String sendingInterval;

    /** 当地实时时间: 0.停用 1.启用 */
    private Integer localRealTimeTime;

    /** 邮件发送提醒: 1.弹窗提醒 2.不提醒 */
    private Integer emailRemindersFlag;

    /** 邮件翻译功能: 0.停用 1.启用 */
    private Integer emailTranslationFunctionFlag;

    /** 待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态 */
    private Integer pendingClose;

    /** 自动化待处理: 0.停用 1.启用 */
    private Integer automationPending;

    /** 异常邮箱检测: 0.停用 1.启用 */
    private Integer abnormalMailboxDetection;
}
