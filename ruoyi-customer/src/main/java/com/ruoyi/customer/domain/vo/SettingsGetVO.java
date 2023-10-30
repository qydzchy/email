package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class SettingsGetVO {
    /** 主键 */
    private Long id;

    /** 子分组必选 0.未选 1.已选 */
    private Integer mandatorySubgroupFlag;

    /** 提前标识 0.关闭 1.启动 */
    private Integer advanceFlag;

    /** 提前几天 */
    private Long advanceDays;

    /** 排除禁用账号 0.未选 1.已选 */
    private Integer accountDisabledFlag;

    /** 领取限制 1.限制 2.不限制 */
    private Integer claimLimitFlag;

    /** 限制几天内，原跟进人不能领取同一个客户 */
    private Long claimLimitDays;

    /** 商机查看规则
     1.领取公海客户后，不能查看客户历史商机
     2.领取公海客户后，可查看客户历史商机
     */
    private Integer opportunityViewRule;

    /** 是否显示公海联系人 0.否 1.是 */
    private Integer showPublicLeadsFlag;

    /** 写跟进时间
     1.提交跟进记录时默认提交时间，不允许修改
     2.提交跟进记录时默认提交时间，可手动修改为历史时间
     */
    private Integer followupTime;
}
