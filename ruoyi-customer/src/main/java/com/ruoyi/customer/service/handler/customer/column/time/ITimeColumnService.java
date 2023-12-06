package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;

import java.util.Date;

public interface ITimeColumnService {

    CustomerColumnEnum getCustomerColumnEnum();

    /**
     * 晚于
     * @param time 时间
     * @param targetCustomerDetail 目标客户详情
     * @return
     */
    boolean after(Date time, CustomerDetailVO targetCustomerDetail);


    /**
     * 早于
     * @param time 时间
     * @param targetCustomerDetail 目标客户详情
     * @return
     */
    boolean before(Date time, CustomerDetailVO targetCustomerDetail);
}
