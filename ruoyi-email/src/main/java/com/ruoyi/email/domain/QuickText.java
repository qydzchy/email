package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 快速文本对象 mailbox_quick_text
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public class QuickText extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 主题 */
    @Excel(name = "主题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** html */
    @Excel(name = "html")
    private String html;

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "QuickText{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", html='" + html + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
