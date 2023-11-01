package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class SegmentListVO {

    /** 主键 */
    private Long id;

    /** 客群名称 */
    private String name;

    /** 使用范围 1.公司共享 2.个人使用 */
    private Integer usageScope;

    /** 可见范围-类型 1.全公司可见 2.全部部门 3.指定部门 4.全部人员 5.指定人员 */
    private Integer visibilityScopeType;

    /** 可见范围-内容 */
    private String visibilityScopeContent;

    /** 条件规则 1.满足全部条件 2.满足任一条件 3.自定义 */
    private Integer conditionRuleType;

    /** 条件规则内容 */
    private String conditionRuleContent;

    /** 添加二级客群 0.未选 1.选中 */
    private Integer subgroupFlag;

    /** 添加规则 1.自动生成 2.手动添加 */
    private Integer additionRule;

    /** 二级客群内容 */
    private String subgroupSegmentContent;
}
