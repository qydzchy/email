package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.customer.domain.bo.CustomerContactBO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerDetailVO {
    /** 主键 */
    private Long id;

    /** 公司网址 */
    @Excel(name = "公司网址")
    private String companyWebsite;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 简称 */
    @Excel(name = "简称")
    private String shortName;

    /** 国家地区 */
    @Excel(name = "国家地区")
    private String countryRegion;

    /** 私海/公海类型 1.私海 2.公海 */
    @Excel(name = "私海/公海类型 1.私海 2.公海")
    private Integer seaType;

    /**
     * 公海分组ID
     */
    private Long publicleadsGroupsId;

    /** 分组ID */
    @Excel(name = "分组ID")
    private Long packetId;

    /** 阶段ID */
    @Excel(name = "阶段ID")
    private Long stageId;

    /** 客户星级 */
    @Excel(name = "客户星级")
    private Integer rating;

    /** 客户编号类型 1.自动生成 2.自定义 */
    @Excel(name = "客户编号类型 1.自动生成 2.自定义")
    private Integer customerNoType;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String customerNo;

    /** 座机-电话区号 */
    @Excel(name = "座机-电话区号")
    private String phonePrefix;

    /** 座机-电话号码 */
    @Excel(name = "座机-电话号码")
    private String phone;

    /** 时区 */
    @Excel(name = "时区")
    private String timezone;

    /** 规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上 */
    @Excel(name = "规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上")
    private Integer scale;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 公司备注 */
    @Excel(name = "公司备注")
    private String companyRemarks;

    /** 公司logo */
    @Excel(name = "公司logo")
    private String companyLogo;

    /** 关注 0.未关注 1.已关注 */
    @Excel(name = "关注 0.未关注 1.已关注")
    private Integer focusFlag;

    /** 最近联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastContactedAt;

    /** 最近跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastFollowupAt;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long createId;

    /** 更新者ID */
    @Excel(name = "更新者ID")
    private Long updateId;

    /**
     * 联系人
     */
    private List<CustomerContactBO> contacts;

    /**
     * 跟进人
     */
    private List<CustomerFollowUpPersonnelListVO> followUpPersonnels;

    /**
     * 写跟进
     */
    private List<CustomerFollowUpRecordsListVO> followUpRecords;

}
