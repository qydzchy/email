package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.vo.EmailPacketVOList;
import com.ruoyi.customer.domain.vo.EmailPublicleadsGroupsVOList;
import com.ruoyi.customer.domain.vo.EmailRatingListVO;
import com.ruoyi.customer.domain.vo.EmailSourceVOList;

import java.util.List;

public interface ICustomerEmailService {

    /**
     * 公海分组列表
     * @return
     */
    List<EmailPublicleadsGroupsVOList> publicleadsGroupsList();

    /**
     * 客户分组列表
     * @return
     */
    List<EmailPacketVOList> packetList();

    /**
     * 客户星级列表
     * @return
     */
    List<EmailRatingListVO> ratingList();

    /**
     * 客户来源列表
     * @return
     */
    List<EmailSourceVOList> sourceList();
}
