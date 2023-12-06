package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;

import java.util.Date;

/**
 * 最近进入私海时间
 */
public class LastPrivateleadsEntryColumn implements ITimeColumnService {
    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_PRIVATELEADS_ENTRY;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date latestEnterPrivateSeaTime = targetCustomerDetail.getLatestEnterPrivateSeaTime();
        if (latestEnterPrivateSeaTime == null) return false;

        return time.after(latestEnterPrivateSeaTime);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date latestEnterPrivateSeaTime = targetCustomerDetail.getLatestEnterPrivateSeaTime();
        if (latestEnterPrivateSeaTime == null) return false;

        return time.before(latestEnterPrivateSeaTime);
    }
}
