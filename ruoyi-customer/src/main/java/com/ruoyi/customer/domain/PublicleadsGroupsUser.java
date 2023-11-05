package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公海分组成员对象 customer_publicleads_groups_user
 * 
 * @author tangJM.
 * @date 2023-11-05
 */
public class PublicleadsGroupsUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 公海分组 */
    @Excel(name = "公海分组")
    private Long publicleadsGroupsId;

    /** 分组成员 */
    @Excel(name = "分组成员")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPublicleadsGroupsId(Long publicleadsGroupsId) 
    {
        this.publicleadsGroupsId = publicleadsGroupsId;
    }

    public Long getPublicleadsGroupsId() 
    {
        return publicleadsGroupsId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("publicleadsGroupsId", getPublicleadsGroupsId())
            .append("userId", getUserId())
            .toString();
    }
}
