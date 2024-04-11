package com.ruoyi.customer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrivateleadsListDTO {

    /**
     * 客群ID
     */
    private Long segmentId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户ID集合
     */
    private List<Long> userIdList;

    /**
     * 分组ID
     */
    private List<Long> packetIdList;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 联系人邮箱
     */
    private String email;

    /**
     * 标签类型 1.包含任一标签 2.包含全部标签
     */
    private Integer tagType;

    /**
     * 标签ID集合
     */
    private List<Long> tagIdList;

    /**
     * 标签ID集合大小
     */
    private Integer tagIdListSize;

    /**
     * 国家/地区集合
     */
    private List<String> countryList;

    /**
     * 来源ID集合
     */
    private List<Long> sourceIdList;

    /**
     * 跟进人ID集合
     */
    private List<Long> followerIdList;

    /**
     * 根据天数
     */
    private Integer followupDays;

    /**
     * 跟进类型 1.未跟进 2.已跟进
     */
    private Integer followupType;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示条数
     */
    private Integer pageSize;
}
