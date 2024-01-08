package com.ruoyi.email.domain.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.bo.EmailAttachmentBO;
import com.ruoyi.email.domain.bo.EmailLabelBO;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class EmailDetailVO {

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    private Long taskId;

    /** 类型 1.收件 2.发件 */
    private Integer type;

    /** 邮件唯一ID */
    private String uid;

    /** 发件人 */
    private String fromer;

    /** 收件人JSON */
    private String receiver;

    /** 密送人JSON,格式和收件人一样 */
    private String bcc;

    /** 抄送人JSON,格式和收件人一样 */
    private String cc;

    /** 所属文件夹,对应邮箱平台文件夹 */
    private String folder;

    /** 文件夹ID,系统文件夹 */
    private Long folderId;

    /** 邮件主题 */
    private String title;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendDate;

    /** 原始邮件存储路径 */
    private String emlPath;

    /** 状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败 */
    private Integer status;

    /** 是否追踪 0.否 1.是 */
    private Integer traceFlag;

    /** 定时发送: 0.否 1.是 */
    private Integer delayedTxFlag;

    /** 收件人所在时区 */
    private String recipientTimeZone;

    /** 收件人当地时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recipientLocalTime;

    /** 是否已读: 0.否 1.是 */
    private Integer readFlag;

    /** 是否待处理: 0.否 1.是 */
    private Integer pendingFlag;

    /** 待处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pendingTime;

    /** 是否固定: 0.否 1.是 */
    private Integer fixedFlag;

    /** 垃圾邮件: 0.否 1.是 */
    private Integer spamFlag;

    /** 每封邮件都应该有一个唯一的 Message-ID */
    private String messageId;

    /** 当你回复一封邮件时，你应该设置 In-Reply-To 头为原始邮件的 Message-ID。 */
    private String inReplyTo;

    /** 邮件链的列表 */
    private String references;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件
     */
    private List<EmailAttachmentBO> emailAttachmentList;

    /**
     * 标签
     */
    private List<EmailLabelBO> emailLabelList;

    /**
     *
     * @return
     */
    public String getSendTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(sendDate);
    }

    public String getFromName() {
        ;return this.fromer.substring(0, this.fromer.indexOf("@"));
    }

    public String getReceiverEmail() {
        JSONArray jsonArray = JSONArray.parseArray(this.receiver);
        if (jsonArray == null || jsonArray.isEmpty()) return "";

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("email");
    }

    public String getReceiverName() {
        JSONArray jsonArray = JSONArray.parseArray(this.receiver);
        if (jsonArray == null || jsonArray.isEmpty()) return "";

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("name");
    }

    public String getToEmail() {
        if (StringUtils.isNotEmpty(this.cc)) {
            JSONArray jsonArray = JSONArray.parseArray(this.cc);
            if (jsonArray == null || jsonArray.isEmpty()) return "";

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return jsonObject.getString("email");
        }

        return "";
    }

    public String getToName() {
        if (StringUtils.isNotEmpty(this.cc)) {
            JSONArray jsonArray = JSONArray.parseArray(this.cc);
            if (jsonArray == null || jsonArray.isEmpty()) return "";

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return jsonObject.getString("name");
        }

        return "";
    }

    public String getExtractTextFromContent() {
        if (this.content == null) return "";

        Document doc = Jsoup.parse(this.content);
        return doc.text();
    }
}
