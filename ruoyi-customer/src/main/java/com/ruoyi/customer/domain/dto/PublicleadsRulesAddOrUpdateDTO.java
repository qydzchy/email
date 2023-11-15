package com.ruoyi.customer.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PublicleadsRulesAddOrUpdateDTO {

    /** 主键 */
    private Long id;

    /** 规则名称 */
    private String name;

    /** 客群ID */
    private List<Long> segmentIdList;

    /** 客户状态-天数 */
    private Long days;

    /** 客户状态-类型1.未联系 2.未成交 */
    private Integer type;

    /** 启用状态 0.关闭 1.启用 */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
}
