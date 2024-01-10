package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.vo.*;

import java.util.List;

public interface ICustomerEmailService {

    /**
     * 公海分组列表
     * @return
     */
    List<EmailPublicleadsGroupsListVO> publicleadsGroupsList();

    /**
     * 客户分组列表
     * @return
     */
    List<EmailPacketListVO> packetList();

    /**
     * 客户星级列表
     * @return
     */
    List<EmailRatingListVO> ratingList();

    /**
     * 客户来源列表
     * @return
     */
    List<EmailSourceListVO> sourceList();

    /**
     * 客户活跃度列表
     * @return
     */
    List<EmailActivityListVO> activityList();

    /**
     * 通用列表
     * @return
     */
    List<EmailGeneralListVO> generalList();

    /**
     * 阶段列表
     * @return
     */
    List<EmailStageListVO> stageList();

    /**
     * 搜索列表
     * @param keyword
     * @return
     */
    List<EmailCustomerVO> searchList(String keyword);

    /**
     * 联系人列表
     * @return
     */
    List<String> contactEmailList(Long customerId);
}
