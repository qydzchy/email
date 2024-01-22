package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmailTranslateDTO {

    @NotBlank(message = "原文语言不能为空")
    private String sourceLanguage;

    @NotBlank(message = "译文语言不能为空")
    private String targetLanguage;

    @NotBlank(message = "翻译的内容不能为空")
    private String sourceText;
}
