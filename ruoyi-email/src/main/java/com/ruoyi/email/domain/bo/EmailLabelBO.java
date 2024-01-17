package com.ruoyi.email.domain.bo;

import lombok.Data;

@Data
public class EmailLabelBO {

    private Long id;

    private Long emailId;

    private String name;

    private String color;

    private Integer type;
}
