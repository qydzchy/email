package com.ruoyi.email.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.email.domain.bo.EmailAttachmentBO;
import com.ruoyi.email.domain.bo.EmailLabelBO;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

@Data
public class DealingEmailListVO {

    private Long id;

    /**
     * 类型 1.收件 2.发件
     */
    private Integer type;

    /**
     * 发送时间
     */
    private String sendDate;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    @JsonIgnore
    private String content;

    /**
     * 标签
     * @return
     */
    private List<EmailLabelBO> labelList;

    /**
     * 附件
     */
    private List<EmailAttachmentBO> emailAttachmentList;

    public String getExtractTextFromContent() {
        if (this.content == null) return "";

        Document doc = Jsoup.parse(this.content);
        return doc.text();
    }
}
