package com.ruoyi.customer.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.enums.customer.CustomerFollowUpTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerRecentActivityBO {

    /**
     * 写跟进ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 跟进类型 1.快速记录 2.电话 3.会面 4.社交平台
     */
    private Integer followUpType;

    /**
     * 跟进内容
     */
    private String followUpContent;

    /**
     * 跟进联系人名称
     */
    private String followUpContactName;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 跟进类型名称
     * @return
     */
    public String getFollowUpTypeName() {
        return CustomerFollowUpTypeEnum.getNameByCode(this.followUpType);
    }
}
