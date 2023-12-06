package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;

import java.util.Date;

/**
 * 创建时间
 */
public class CreateTimeColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CREATE_TIME;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date createTime = targetCustomerDetail.getCreateTime();
        if (createTime == null) return false;

        return time.after(createTime);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date createTime = targetCustomerDetail.getCreateTime();
        if (createTime == null) return false;

        return time.before(createTime);
    }
}
