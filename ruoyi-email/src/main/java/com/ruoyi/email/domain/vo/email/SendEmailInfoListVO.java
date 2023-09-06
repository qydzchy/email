package com.ruoyi.email.domain.vo.email;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SendEmailInfoListVO {

    /** 主键 */
    private Long id;

    /** 邮箱任务ID */
    private Long taskId;

    /** 发件人 */
    private String fromer;

    /** 收件人JSON */
    private String receiver;

    /** 密送人JSON,格式和收件人一样 */
    private String bcc;

    /** 抄送人JSON,格式和收件人一样 */
    private String cc;

    /** 邮件主题 */
    private String title;

    /** 状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败 */
    private Integer status;

    /** 定时发送: 0.否 1.是 */
    private Integer delayedTxFlag;

    /** 收件人所在时区 */
    private String recipientTimeZone;

    /** 收件人当地时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recipientLocalTime;

    /** 是否待处理: 0.否 1.是 */
    private Integer pendingFlag;

    /** 待处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pendingTime;

    /** 是否固定: 0.否 1.是 */
    private Integer fixedFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 邮件内容
     */
    private String content;


    public String getSendTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(createTime);
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
