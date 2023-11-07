package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 客户详情
 */
@Data
public class CustomerDetailVO {

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
     * 客户分组ID
     */
    private Long packetId;

    /**
     * 客户分组名称
     */
    private String packetName;

    /**
     * 客户阶段ID
     */
    private Long stageId;

    /**
     * 客户阶段名称
     */
    private String stageName;

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
