package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SegmentListVO {

    /** 主键 */
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /** 客群名称 */
    private String name;

    /** 使用范围 1.公司共享 2.个人使用 */
    private Integer usageScope;

    /** 可见范围 */
    private String visibilityScope;

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

    /**
     * 客户数量
     */
    private Integer customerCount;

    private List<SegmentListVO> children = new ArrayList<>();

    /**
     * 可见范围名称
     * @return
     */
    public String getUsageScopeName() {
        if (usageScope != null) {
            if (usageScope.intValue() == 1) {
                return "全公司可见";
            } else if (usageScope.intValue() == 2) {
                return "仅个人使用";
            }
        }

        return "";
    }
}
