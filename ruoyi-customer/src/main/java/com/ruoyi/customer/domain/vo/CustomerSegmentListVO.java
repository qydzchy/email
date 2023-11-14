package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerSegmentListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 客群名称
     */
    private String name;

    /**
     * 客户数
     */
    private Integer customerCount;


    private List<CustomerSegmentListVO> children = new ArrayList<>();
}
