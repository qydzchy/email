package com.ruoyi.email.domain.vo.template;

import lombok.Data;

@Data
public class TemplateListVO {

    private Long id;

    private String name;

    private Integer templateTypeId;

    private String title;

    private String content;
}
