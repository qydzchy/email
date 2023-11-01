package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerSimpleListVO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 客户标签
     */
    private List<TagListVO> tagList;

    /**
     * 客户分组
     */
    private List<PacketListVO> packetList;

    /**
     * 客户阶段
     */
    private List<StageListVO> stageList;

    /**
     * 主要联系人
     */
    private String primaryContact;

    /**
     * 最近动态
     */
    private String recentActivity;

    /**
     * 最近联系时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastContactedAt;

    /**
     * 国家地区
     */
    private String countryRegion;
}
