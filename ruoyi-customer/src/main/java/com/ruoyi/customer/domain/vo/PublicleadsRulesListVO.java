package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.enums.customer.PublicleadsRulesTypeEnum;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PublicleadsRulesListVO {

    /**
     * ID
     */
    private Long id;

    /** 规则名称 */
    private String name;

    /** 客群ID */
    private String segmentIds;

    /** 客户状态-天数 */
    private Long days;

    /** 客户状态-类型1.未联系 2.未成交 */
    private Integer type;

    /** 启用状态 0.关闭 1.启用 */
    private Integer status;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 创建者ID
     */
    private Long createId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public List<Long> getSegmentIdList() {
        List<Long> segmentIdList = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(this.segmentIds)) {
                String[] segmentIdArr = this.segmentIds.split(",");
                for (String segmentIdStr : segmentIdArr) {
                    segmentIdList.add(Long.valueOf(segmentIdStr));
                }
            }
        } catch (Exception e) {}

        return segmentIdList;
    }

}
