package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.customer.domain.bo.CustomerContactBO;
import com.ruoyi.customer.domain.bo.CustomerRecentActivityBO;
import lombok.Data;

import java.util.Date;

@Data
public class PublicleadsCustomerSimpleListVO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 最近跟进
     */
    private CustomerRecentActivityBO recentFollowUp;

    /**
     * 最近动态
     */
    private CustomerRecentActivityBO recentActivity;

    /**
     * 原跟进人
     */
    private String originalFollowUp;

    /**
     * 国家地区
     */
    private String countryRegion;

    /**
     * 客户类型
     */
    private Integer customerType;

    /**
     * 客户评分
     */
    private Integer customerScore;

    /**
     * 最近联系时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastContactedAt;

    /**
     * 时区
     */
    private String timezone;

    /**
     * 联系人信息
     */
    private CustomerContactBO contact;

    public String getCustomerTypeName() {
        return null;
    }
}
