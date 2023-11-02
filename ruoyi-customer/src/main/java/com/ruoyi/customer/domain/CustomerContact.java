package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户联系人对象 customer_customer_contact
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public class CustomerContact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 客户详情ID */
    @Excel(name = "客户详情ID")
    private Long customerId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 社交平台
[{"type":1,"account":""}]
 */
    @Excel(name = "社交平台")
    private String socialPlatform;

    /** 联系电话
[{"phone_prefix":"","phone":""}]
 */
    @Excel(name = "联系电话")
    private String phone;

    /** 职级 1.普通职员 2.中层管理者 3.高层管理者 */
    @Excel(name = "职级 1.普通职员 2.中层管理者 3.高层管理者")
    private Integer rank;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 性别 1.不设置 2.男 3.女 */
    @Excel(name = "性别 1.不设置 2.男 3.女")
    private Integer sex;

    /** 头像/名片 */
    @Excel(name = "头像/名片")
    private String avatarOrBusinessCard;

    /** 联系人备注 */
    @Excel(name = "联系人备注")
    private String contactRemarks;

    /** 是否为主要联系人 1.是 0.否 */
    @Excel(name = "是否为主要联系人 1.是 0.否")
    private Integer primaryContactFlag;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderIndex;

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
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setSocialPlatform(String socialPlatform) 
    {
        this.socialPlatform = socialPlatform;
    }

    public String getSocialPlatform() 
    {
        return socialPlatform;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setRank(Integer rank) 
    {
        this.rank = rank;
    }

    public Integer getRank() 
    {
        return rank;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setSex(Integer sex) 
    {
        this.sex = sex;
    }

    public Integer getSex() 
    {
        return sex;
    }
    public void setAvatarOrBusinessCard(String avatarOrBusinessCard) 
    {
        this.avatarOrBusinessCard = avatarOrBusinessCard;
    }

    public String getAvatarOrBusinessCard() 
    {
        return avatarOrBusinessCard;
    }
    public void setContactRemarks(String contactRemarks) 
    {
        this.contactRemarks = contactRemarks;
    }

    public String getContactRemarks() 
    {
        return contactRemarks;
    }
    public void setPrimaryContactFlag(Integer primaryContactFlag) 
    {
        this.primaryContactFlag = primaryContactFlag;
    }

    public Integer getPrimaryContactFlag() 
    {
        return primaryContactFlag;
    }
    public void setOrderIndex(Long orderIndex) 
    {
        this.orderIndex = orderIndex;
    }

    public Long getOrderIndex() 
    {
        return orderIndex;
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
            .append("customerId", getCustomerId())
            .append("nickName", getNickName())
            .append("email", getEmail())
            .append("socialPlatform", getSocialPlatform())
            .append("phone", getPhone())
            .append("rank", getRank())
            .append("position", getPosition())
            .append("birthday", getBirthday())
            .append("sex", getSex())
            .append("avatarOrBusinessCard", getAvatarOrBusinessCard())
            .append("contactRemarks", getContactRemarks())
            .append("primaryContactFlag", getPrimaryContactFlag())
            .append("orderIndex", getOrderIndex())
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
