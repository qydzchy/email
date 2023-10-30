package com.ruoyi.customer.domain.vo;

import lombok.Data;

@Data
public class FollowUpRulesListVO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /** 类型
      1. 获取公海/同事客户 2. 发送邮件 (客户+商机) 3. 接收邮件 (客户+商机) 4. 营销邮件 (客户+商机) 5. 编辑客户 6. 新建跟进 (客户+商机) 7. 新建/编辑 商机 8. 新建/编辑 报价单 9. 新建/编辑 销售订单 10. 上传客户文档 11. 发送聊天消息 (TM+WhatsAPP+FB Messenger) 12. 接收聊天消息 (TM+WhatsAPP+FB Messenger) 13. 发送聊天消息 (TM+WhatsAPP+FB Messenger) 14. 发送邮件 (客户+商机) 15. 营销邮件 (客户+商机) 16. 新建跟进 (客户+商机) */
    private Integer type;

    /**
     * 类别 1. 发生以下行为会影响客户的“最近联系时间”，进而影响客户被自动移入公海的时间
     * 2. 发生以下行为会影响客户的“最近跟进时间”，进而影响跟进客户类型的任务截止时间
     */
    private Integer category;

    /**
     * 标志 0.未选 1.选中
     */
    private Boolean activeFlag;
}
