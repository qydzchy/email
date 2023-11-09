package com.ruoyi.customer.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SegmentAddOrUpdateDTO {

    /** 主键 */
    private Long id;

    /** 父ID 第一级为-1 */
    private Long parentId;

    /** 客群名称 */
    @NotBlank(message = "客群名称不能为空")
    private String name;

    /** 使用范围 1.公司共享 2.个人使用 */
    private Integer usageScope;

    /** 可见范围-内容 */
    private String visibilityScope;

    /** 条件规则类型 1.满足全部条件 2.满足任一条件 3.自定义条件 */
    @NotNull(message = "条件规则类型不能为空")
    private Integer conditionRuleType;

    /** 条件规则内容 */
    @NotBlank(message = "条件规则内容不能为空")
    private String conditionRuleContent;

    /** 添加二级客群 0.未选 1.选中 */
    private Integer subgroupFlag;

    /** 添加规则 1.自动生成 2.手动添加 */
    private Integer additionRule;

    /**
     * 二级客群字段
     */
    private Long secondarySegmentColumnId;

    /**
     * 二级客群
     */
    private List<SegmentAddOrUpdateDTO> children;
}
