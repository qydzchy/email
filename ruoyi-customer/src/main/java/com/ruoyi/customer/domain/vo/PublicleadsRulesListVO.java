package com.ruoyi.customer.domain.vo;

import com.ruoyi.common.enums.customer.PublicleadsRulesTypeEnum;
import lombok.Data;

@Data
public class PublicleadsRulesListVO {

    /**
     * ID
     */
    private Long id;

    /** 规则名称 */
    private String name;

    /** 客群ID */
    private Long customerSegmentId;

    /** 客户状态-天数 */
    private Long days;

    /** 客户状态-类型1.未联系 2.未成交 */
    private Integer type;

    /** 启用状态 0.关闭 1.启用 */
    private Integer status;

    /**
     * 移入条件
     * @return
     */
    public String getInclusionConditions() {
        PublicleadsRulesTypeEnum publicleadsRulesTypeEnum = PublicleadsRulesTypeEnum.getByType(this.type);
        if (publicleadsRulesTypeEnum != null) {
            return this.days + "天" + publicleadsRulesTypeEnum.getTypeName();
        }

        return "";
    }

}
