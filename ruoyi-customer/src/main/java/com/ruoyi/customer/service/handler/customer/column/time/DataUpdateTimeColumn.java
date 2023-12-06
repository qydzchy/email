package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 资料更新时间
 */
@Component
public class DataUpdateTimeColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.DATA_UPDATE_TIME;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date updateTime = targetCustomerDetail.getUpdateTime();
        if (updateTime == null) return false;

        return time.after(updateTime);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date updateTime = targetCustomerDetail.getUpdateTime();
        if (updateTime == null) return false;

        return time.before(updateTime);
    }
}
