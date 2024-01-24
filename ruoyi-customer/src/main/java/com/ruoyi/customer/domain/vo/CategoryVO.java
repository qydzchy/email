package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {

    /**
     * 获取近7天往来
     */
    private List<EmailCustomerVO> last7Days;

    /**
     * 公海分组
     */
    private List<EmailPublicleadsGroupsListVO> publicleadsGroups;

    /**
     * 客户分组
     */
    private List<EmailPacketListVO> packet;

    /**
     * 客户来源
     */
    private List<EmailSourceListVO> source;

    /**
     * 客户状态
     */
    private List<EmailStageListVO> stage;

    /**
     * 客户星级
     */
    private List<EmailRatingListVO> rating;

    /**
     * 客户活跃度
     */
    private List<EmailActivityListVO> activity;
}
