package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class EmailRatingListVO {

    private Integer rating;

    private Integer customerCount;

    private Integer emailCount;
}
