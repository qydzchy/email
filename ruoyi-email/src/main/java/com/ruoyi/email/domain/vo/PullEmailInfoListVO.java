package com.ruoyi.email.domain.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class PullEmailInfoListVO {

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    private Long taskId;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date sendDate;

    /** 原始邮件存储路径 */
    private String emlPath;

    /** 是否已读: 0.否 1.是 */
    private Boolean readFlag;

    /** 是否待处理: 0.否 1.是 */
    private Boolean pendingFlag;

    /** 待处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date pendingTime;

    /** 是否固定: 0.否 1.是 */
    private Boolean fixedFlag;

    /** 垃圾邮件: 0.否 1.是 */
    private Boolean spamFlag;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件
     */
    private List<AttachmentVO> attachmentList;

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

    public String getExtractTextFromContent() {
        if (this.content == null) return "";

        Document doc = Jsoup.parse(this.content);
        return doc.text();
    }
}
