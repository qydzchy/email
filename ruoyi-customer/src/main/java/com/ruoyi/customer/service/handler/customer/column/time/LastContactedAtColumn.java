package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 最近联系时间
 */
@Component
public class LastContactedAtColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_CONTACTED_AT;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date lastContactedAt = targetCustomerDetail.getLastContactedAt();
        if (lastContactedAt == null) return false;

        return time.after(lastContactedAt);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date lastContactedAt = targetCustomerDetail.getLastContactedAt();
        if (lastContactedAt == null) return false;

        return time.before(lastContactedAt);
    }
}
