package com.ruoyi.email.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.email.domain.vo.TransceiverRuleVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 邮箱操作参数
 */
@Data
public class EmailOperateParamBO {

    /**
     * 每页显示邮件数量
     */
    private Integer maxPerPage;

    /** 自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次) */
    private Integer autoResponseFlag;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 最后一天标识 1.勾选 0未选 */
    private Integer lastDayFlag;

    /** 最后一天 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastDay;

    /** 回复内容 */
    private String reContent;

    /** 邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送 */
    private Integer delayedMailDelivery;

    /** 发送间隔 */
    private String sendingInterval;

    /** 待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态 */
    private Integer pendingClose;

    /** 自动化待处理: 0.停用 1.启用 */
    private Integer automationPending;

    /** 异常邮箱检测: 0.停用 1.启用 */
    private Integer abnormalMailboxDetection;

    /**
     * 收发件规则
     */
    private List<TransceiverRuleVO> transceiverRuleList;

    public Integer getMaxPerPage() {
        return this.maxPerPage != null ? this.maxPerPage : 100;
    }
}
