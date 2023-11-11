package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class FollowUpRecordsCommentListVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private String operatorTime;
}
