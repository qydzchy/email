package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客群对象 customer_segment
 *
 * @author tangJM.
 * @date 2023-11-02
 */
public class Segment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 父ID 第一级为-1 */
    @Excel(name = "父ID 第一级为-1")
    private Long parentId;

    /** 客群名称 */
    @Excel(name = "客群名称")
    private String name;

    /** 使用范围 1.公司共享 2.个人使用 */
    @Excel(name = "使用范围 1.公司共享 2.个人使用")
    private Integer usageScope;

    /** 可见范围-类型 1.全公司可见 2.全部部门 3.指定部门 4.全部人员 5.指定人员 */
    @Excel(name = "可见范围-类型 1.全公司可见 2.全部部门 3.指定部门 4.全部人员 5.指定人员")
    private Integer visibilityScopeType;

    /** 可见范围-内容 */
    @Excel(name = "可见范围-内容")
    private String visibilityScopeContent;

    /** 条件规则 1.满足全部条件 2.满足任一条件 3.自定义条件 */
    @Excel(name = "条件规则 1.满足全部条件 2.满足任一条件 3.自定义条件")
    private Integer conditionRuleType;

    /** 条件规则内容 */
    @Excel(name = "条件规则内容")
    private String conditionRuleContent;

    /** 添加二级客群 0.未选 1.选中 */
    @Excel(name = "添加二级客群 0.未选 1.选中")
    private Integer subgroupFlag;

    /** 添加规则 1.自动生成 2.手动添加 */
    @Excel(name = "添加规则 1.自动生成 2.手动添加")
    private Integer additionRule;

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
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setUsageScope(Integer usageScope)
    {
        this.usageScope = usageScope;
    }

    public Integer getUsageScope()
    {
        return usageScope;
    }
    public void setVisibilityScopeType(Integer visibilityScopeType)
    {
        this.visibilityScopeType = visibilityScopeType;
    }

    public Integer getVisibilityScopeType()
    {
        return visibilityScopeType;
    }
    public void setVisibilityScopeContent(String visibilityScopeContent)
    {
        this.visibilityScopeContent = visibilityScopeContent;
    }

    public String getVisibilityScopeContent()
    {
        return visibilityScopeContent;
    }
    public void setConditionRuleType(Integer conditionRuleType)
    {
        this.conditionRuleType = conditionRuleType;
    }

    public Integer getConditionRuleType()
    {
        return conditionRuleType;
    }
    public void setConditionRuleContent(String conditionRuleContent)
    {
        this.conditionRuleContent = conditionRuleContent;
    }

    public String getConditionRuleContent()
    {
        return conditionRuleContent;
    }
    public void setSubgroupFlag(Integer subgroupFlag)
    {
        this.subgroupFlag = subgroupFlag;
    }

    public Integer getSubgroupFlag()
    {
        return subgroupFlag;
    }
    public void setAdditionRule(Integer additionRule)
    {
        this.additionRule = additionRule;
    }

    public Integer getAdditionRule()
    {
        return additionRule;
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
                .append("parentId", getParentId())
                .append("name", getName())
                .append("usageScope", getUsageScope())
                .append("visibilityScopeType", getVisibilityScopeType())
                .append("visibilityScopeContent", getVisibilityScopeContent())
                .append("conditionRuleType", getConditionRuleType())
                .append("conditionRuleContent", getConditionRuleContent())
                .append("subgroupFlag", getSubgroupFlag())
                .append("additionRule", getAdditionRule())
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
