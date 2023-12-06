package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 最近跟进时间
 */
@Component
public class LastFollowupAtColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_FOLLOWUP_AT;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date lastFollowupAt = targetCustomerDetail.getLastFollowupAt();
        if (lastFollowupAt == null) return false;

        return time.after(lastFollowupAt);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date lastFollowupAt = targetCustomerDetail.getLastFollowupAt();
        if (lastFollowupAt == null) return false;

        return time.before(lastFollowupAt);
    }
}
