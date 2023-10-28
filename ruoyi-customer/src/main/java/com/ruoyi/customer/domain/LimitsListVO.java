package com.ruoyi.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LimitsListVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 客户上限ID
     */
    private Long id;

    /**
     * 类型 1.不限 2.客户上限
     */
    private Integer type;

    /**
     * 客户上限值
     */
    private Integer limits;

    /**
     * 客户数
     * @return
     */
    private Integer customerCount;

    public Integer getType() {
        return type == null ? 1 : type;
    }

    public String getContent() {
        String typeName = this.type == null ? "不限" : "(上限)";
        this.limits = this.limits == null ? 0 : this.limits;
        this.customerCount = this.customerCount == null ? 0 : this.customerCount;
        String content = String.format("客户 %s/%s%s", this.customerCount, this.limits, typeName);
        return content;
    }
}
