package com.ruoyi.email.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuCountVO {
    /**
     * 全部收件
     */
    private Integer allReceivedCount;

    /**
     * 收件箱邮件数量
     */
    private List<MenuInboxTaskCountVO> menuInboxTaskCountList;

    /**
     * 待处理邮件
     */
    private Integer pendingMailCount;

    /**
     * 未读邮件
     */
    private Integer anUnreadMailCount;

    /**
     * 草稿箱
     */
    private Integer draftsCount;
}
