package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.customer.domain.bo.CustomerContactBO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerDetailVO {
    /** 主键 */
    private Long id;

    /** 公司网址 */
    private String companyWebsite;

    /** 公司名称 */
    private String companyName;

    /** 简称 */
    private String shortName;

    /** 国家地区 */
    private String countryRegion;

    /** 私海/公海类型 1.私海 2.公海 */
    private Integer seaType;

    /** 客户星级 */
    private Integer rating;

    /** 客户编号类型 1.自动生成 2.自定义 */
    private Integer customerNoType;

    /** 客户编号 */
    private String customerNo;

    /** 座机-电话区号 */
    private String phonePrefix;

    /** 座机-电话号码 */
    private String phone;

    /** 时区 */
    private String timezone;

    /** 规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上 */
    private Integer scale;

    /** 传真 */
    private String fax;

    /** 详细地址 */
    private String address;

    /** 公司备注 */
    private String companyRemarks;

    /** 公司logo */
    private String companyLogo;

    /** 关注 0.未关注 1.已关注 */
    private Integer focusFlag;

    /** 最近联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastContactedAt;

    /** 最近跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastFollowupAt;

    /** 创建者ID */
    private Long createId;

    /** 更新者ID */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 联系人
     */
    private List<CustomerContactBO> contactList;

    /**
     * 跟进人
     */
    private List<CustomerFollowUpPersonnelListVO> followUpPersonnelList;

    /**
     * 写跟进
     */
    private List<CustomerFollowUpRecordsListVO> followUpRecordsList;

    /**
     * 客户标签
     */
    private List<CustomerTagListVO> tagList;

    /**
     * 客户来源
     */
    private List<SourceListVO> sourceList;

    /**
     * 客户阶段
     */
    private StageListVO stage;

    /**
     * 客户分组
     */
    private SimplePacketVO packet;

    /**
     * 公海分组
     */
    private PublicleadsGroupsListVO publicleadsGroups;
}
