package com.ruoyi.customer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerScheduleListDTO {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 参与人员id
     */
    private List<Long> userIds;
}
