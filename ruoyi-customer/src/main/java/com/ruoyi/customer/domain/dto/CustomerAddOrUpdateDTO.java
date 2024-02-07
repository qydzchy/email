package com.ruoyi.customer.domain.dto;

import com.ruoyi.customer.domain.CustomerFollowUpPersonnel;
import com.ruoyi.customer.domain.CustomerSeaLog;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CustomerAddOrUpdateDTO {

    /** 主键 */
    private Long id;

    /** 公司网址 */
    private String companyWebsite;

    /** 公司名称 */
    @NotBlank(message = "公司名称不能为空")
    private String companyName;

    /** 简称 */
    private String shortName;

    /** 国家地区 */
    private String countryRegion;

    private List<Long> sourceIds;

    private List<Long> tagIds;

    /** 私海/公海类型 1.私海 2.公海 */
    @NotNull(message = "私海/公海类型不能为空")
    private Integer seaType;

    /**
     * 公海分组ID
     */
    private Long publicleadsGroupsId;

    /** 分组ID */
    private Long packetId;

    /** 阶段ID */
    private Long stageId;

    /** 客户星级 */
    private Integer rating;

    /** 客户编号类型 1.自动生成 2.自定义 */
    @NotNull(message = "客户编号类型不能为空")
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

    /**
     * 更新人
     */
    private Long updateId;

    /**
     * 更新人名称
     */
    private String updateBy;

    /**
     * 最近跟进时间
     */
    private Date lastFollowupAt;

    /**
     * 客户联系人
     */
    private List<CustomerContactAddOrUpdateDTO> contactList;

    /**
     * 移入公/私海时间
     */
    private CustomerSeaLog customerSeaLog;

    /**
     * 跟进人
     */
    private CustomerFollowUpPersonnel customerFollowUpPersonnel;
}
