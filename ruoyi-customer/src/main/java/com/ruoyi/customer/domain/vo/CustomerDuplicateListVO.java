package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 客户查重列表
 */
@Data
public class CustomerDuplicateListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 阶段名称
     */
    private String customerStageName;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 联系人名
     */
    private String contactNickName;

    /**
     * 客户邮箱
     */
    private String customerEmail;

    /**
     * 客户电话
     */
    private String customerPhone;

    /**
     * 客户来源
     */
    private String customerSourceName;

    /**
     * 国家地区
     */
    private String countryRegion;

    /**
     * 原跟进人
     */
    private String sourceFollowUpPersonnel;

    /**
     * 当前归属
     */
    private String currentFollowUpPersonnel;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 社交平台
     */
    private String socialPlatform;
}
