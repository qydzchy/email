package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户元数据列对象 customer_metadata_column
 *
 * @author tangJM.
 * @date 2023-11-01
 */
public class MetadataColumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String columnName;

    /** 字段别名 */
    @Excel(name = "字段别名")
    private String columnAlias;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String columnType;

    /** 应用类型 1.客户查重 2.客户列表 3.公海客户 */
    @Excel(name = "应用类型 1.客户查重 2.客户列表 3.公海客户")
    private Integer appType;

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
    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public String getColumnName()
    {
        return columnName;
    }
    public void setColumnAlias(String columnAlias)
    {
        this.columnAlias = columnAlias;
    }

    public String getColumnAlias()
    {
        return columnAlias;
    }
    public void setColumnType(String columnType)
    {
        this.columnType = columnType;
    }

    public String getColumnType()
    {
        return columnType;
    }
    public void setAppType(Integer appType)
    {
        this.appType = appType;
    }

    public Integer getAppType()
    {
        return appType;
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
                .append("columnName", getColumnName())
                .append("columnAlias", getColumnAlias())
                .append("columnType", getColumnType())
                .append("appType", getAppType())
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
