package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;

import java.util.Date;

/**
 * 最近进入公海时间
 */
public class LastPublicleadsEntryColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_PUBLICLEADS_ENTRY;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date latestEnterPublicSeaTime = targetCustomerDetail.getLatestEnterPublicSeaTime();
        if (latestEnterPublicSeaTime == null) return false;

        return time.after(latestEnterPublicSeaTime);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date latestEnterPublicSeaTime = targetCustomerDetail.getLatestEnterPublicSeaTime();
        if (latestEnterPublicSeaTime == null) return false;

        return time.before(latestEnterPublicSeaTime);
    }
}
