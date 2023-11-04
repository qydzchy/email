package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户详情对象 customer_customer
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long createId;

    /** 更新者ID */
    @Excel(name = "更新者ID")
    private Long updateId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyWebsite(String companyWebsite) 
    {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyWebsite() 
    {
        return companyWebsite;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setShortName(String shortName) 
    {
        this.shortName = shortName;
    }

    public String getShortName() 
    {
        return shortName;
    }
    public void setCountryRegion(String countryRegion) 
    {
        this.countryRegion = countryRegion;
    }

    public String getCountryRegion() 
    {
        return countryRegion;
    }
    public void setSeaType(Integer seaType) 
    {
        this.seaType = seaType;
    }

    public Integer getSeaType() 
    {
        return seaType;
    }

    public Long getPublicleadsGroupsId() {
        return publicleadsGroupsId;
    }

    public void setPublicleadsGroupsId(Long publicleadsGroupsId) {
        this.publicleadsGroupsId = publicleadsGroupsId;
    }

    public void setPacketId(Long packetId)
    {
        this.packetId = packetId;
    }

    public Long getPacketId() 
    {
        return packetId;
    }
    public void setStageId(Long stageId) 
    {
        this.stageId = stageId;
    }

    public Long getStageId() 
    {
        return stageId;
    }
    public void setRating(Integer rating) 
    {
        this.rating = rating;
    }

    public Integer getRating() 
    {
        return rating;
    }
    public void setCustomerNoType(Integer customerNoType) 
    {
        this.customerNoType = customerNoType;
    }

    public Integer getCustomerNoType() 
    {
        return customerNoType;
    }
    public void setCustomerNo(String customerNo) 
    {
        this.customerNo = customerNo;
    }

    public String getCustomerNo() 
    {
        return customerNo;
    }
    public void setPhonePrefix(String phonePrefix) 
    {
        this.phonePrefix = phonePrefix;
    }

    public String getPhonePrefix() 
    {
        return phonePrefix;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setTimezone(String timezone) 
    {
        this.timezone = timezone;
    }

    public String getTimezone() 
    {
        return timezone;
    }
    public void setScale(Integer scale) 
    {
        this.scale = scale;
    }

    public Integer getScale() 
    {
        return scale;
    }
    public void setFax(String fax) 
    {
        this.fax = fax;
    }

    public String getFax() 
    {
        return fax;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCompanyRemarks(String companyRemarks) 
    {
        this.companyRemarks = companyRemarks;
    }

    public String getCompanyRemarks() 
    {
        return companyRemarks;
    }
    public void setCompanyLogo(String companyLogo) 
    {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogo() 
    {
        return companyLogo;
    }
    public void setFocusFlag(Integer focusFlag) 
    {
        this.focusFlag = focusFlag;
    }

    public Integer getFocusFlag() 
    {
        return focusFlag;
    }
    public void setLastContactedAt(Date lastContactedAt) 
    {
        this.lastContactedAt = lastContactedAt;
    }

    public Date getLastContactedAt() 
    {
        return lastContactedAt;
    }
    public void setLastFollowupAt(Date lastFollowupAt) 
    {
        this.lastFollowupAt = lastFollowupAt;
    }

    public Date getLastFollowupAt() 
    {
        return lastFollowupAt;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }
    public void setUpdateId(Long updateId) 
    {
        this.updateId = updateId;
    }

    public Long getUpdateId() 
    {
        return updateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyWebsite", getCompanyWebsite())
            .append("companyName", getCompanyName())
            .append("shortName", getShortName())
            .append("countryRegion", getCountryRegion())
            .append("seaType", getSeaType())
            .append("packetId", getPacketId())
            .append("stageId", getStageId())
            .append("rating", getRating())
            .append("customerNoType", getCustomerNoType())
            .append("customerNo", getCustomerNo())
            .append("phonePrefix", getPhonePrefix())
            .append("phone", getPhone())
            .append("timezone", getTimezone())
            .append("scale", getScale())
            .append("fax", getFax())
            .append("address", getAddress())
            .append("companyRemarks", getCompanyRemarks())
            .append("companyLogo", getCompanyLogo())
            .append("focusFlag", getFocusFlag())
            .append("lastContactedAt", getLastContactedAt())
            .append("lastFollowupAt", getLastFollowupAt())
            .append("delFlag", getDelFlag())
            .append("createId", getCreateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
