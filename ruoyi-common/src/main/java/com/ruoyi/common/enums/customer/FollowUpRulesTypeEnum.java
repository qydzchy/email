package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum FollowUpRulesTypeEnum {
    GET_PUBLIC_SEA_COLLEAGUE_CUSTOMER(1, "获取公海/同事客户"),
    SEND_EMAIL_CUSTOMER_OPPORTUNITY(2, "发送邮件 (客户+商机)"),
    RECEIVE_EMAIL_CUSTOMER_OPPORTUNITY(3, "接收邮件 (客户+商机)"),
    MARKETING_EMAIL_CUSTOMER_OPPORTUNITY(4, "营销邮件 (客户+商机)"),
    EDIT_CUSTOMER(5, "编辑客户"),
    NEW_FOLLOW_UP_CUSTOMER_OPPORTUNITY(6, "新建跟进 (客户+商机)"),
    NEW_EDIT_OPPORTUNITY(7, "新建/编辑 商机"),
    NEW_EDIT_QUOTE(8, "新建/编辑 报价单"),
    NEW_EDIT_SALES_ORDER(9, "新建/编辑 销售订单"),
    UPLOAD_CUSTOMER_DOCUMENT(10, "上传客户文档"),
    SEND_CHAT_MESSAGE(11, "发送聊天消息 (TM+WhatsAPP+FB Messenger)"),
    RECEIVE_CHAT_MESSAGE(12, "接收聊天消息 (TM+WhatsAPP+FB Messenger)"),
    SEND_EMAIL_CUSTOMER_OPPORTUNITY_MARKETING(13, "发送邮件 (客户+商机) 营销"),
    MARKETING_EMAIL_CUSTOMER_OPPORTUNITY_ADDITIONAL(14, "营销邮件 (客户+商机)"),
    NEW_FOLLOW_UP_CUSTOMER_OPPORTUNITY_ADDITIONAL(15, "新建跟进 (客户+商机)"),
    SEND_EMAIL_CUSTOMER_OPPORTUNITY_ADDITIONAL(16, "发送邮件 (客户+商机)");

    private int type;
    private String description;

    public static FollowUpRulesTypeEnum getFollowUpRulesTypeEnumByType(Integer type) {
        if (type == null) return null;

        for (FollowUpRulesTypeEnum followUpRulesTypeEnum : FollowUpRulesTypeEnum.values()) {
            if (followUpRulesTypeEnum.getType() == type.intValue()) {
                return followUpRulesTypeEnum;
            }
        }
        return null;
    }
}
