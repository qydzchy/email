package com.ruoyi.email.domain.dto.email;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class EmailListDTO {

    /**
     * 邮箱ID
     */
    private Long taskId;

    /**
     * 邮箱ID列表
     */
    private List<Long> taskIdList;

    /**
     * 状态列表
     */
    private List<Integer> statusList;

    /**
     * 是否已读: 0.否 1.是
     */
    private Boolean readFlag;

    /**
     * 是否待处理: 0.否 1.是
     */
    private Boolean pendingFlag;

    /**
     * 是否为草稿箱
     */
    private Boolean draftsFlag;

    /**
     * 垃圾邮件: 0.否 1.是
     */
    private Boolean spamFlag;

    /**
     * 是否追踪 0.否 1.是
     */
    private Boolean traceFlag;

    /**
     * 是否固定: 0.否 1.是
     */
    private Boolean fixedFlag;

    /**
     * 是否存在附件：0.否 1.是
     */
    private Boolean attachmentFlag;

    /**
     * 是否存在客户：0.否 1.是
     */
    private Boolean customerFlag;

    /**
     * 文件夹ID
     */
    private Long folderId;

    /**
     * 标签ID
     */
    private Long labelId;

    /**
     * 是否删除 '0'未删 '2'已删
     */
    private String delFlag;

    /**
     * 类型 1.收件 2.发件
     */
    private Integer type;


    /**
     * 关键字类型 1.主题  2.正文 3.附件名
     */
    private Integer keywordType;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 客户关键字
     */
    private String customerKeyword;

    /**
     * 收发件类型 1.发件人或收件人 2.发件人和收件人
     */
    private Integer senderRecipientType;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 发件人
     */
    private String sender;

    /**
     * 邮件标签类型 1.包含任意标签 2.包含全部标签
     */
    private Integer labelType;

    /**
     * 标签ID
     */
    private List<Long> labelIdList;

    /**
     * 开始发送时间
     */
    private String startSendDate;

    /**
     * 结束发送时间
     */
    private String endSendDate;

    /**
     * 文件夹类型 1.全部 2.指定文件夹
     */
    private Long folderType;

    /**
     * 附件类型 1.全部 2.含附件 3.不含附件
     */
    private Integer attachmentType;

    /**
     * 打钉类型 1.不限 2.有 3.无
     */
    private Integer fixedType;

    /**
     * 收发类型 1.不限 2.收取的 3.发送的
     */
    private Integer transceiverType;

    /**
     * 发送情况类型 1.不限 2.发送失败 3.发送成功
     */
    private Integer sendType;

    /**
     * 高级搜索 true是 false否
     */
    private Boolean advancedSearchFlag;

    /**
     * 用户ID
     */
    private Long createId;

    public Boolean getSpamFlag() {
        return Optional.ofNullable(spamFlag).orElse(false);
    }

    public String getDelFlag() {
        return Optional.ofNullable(delFlag).orElse("0");
    }

    public Boolean getAdvancedSearchFlag() {
        return Optional.ofNullable(advancedSearchFlag).orElse(false);
    }
}
