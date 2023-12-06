package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 下次移入公海日期
 */
@Component
public class NextPublicleadsDateColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.NEXT_PUBLICLEADS_DATE;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date latestMoveToPublicSeaTime = targetCustomerDetail.getLatestMoveToPublicSeaTime();
        if (latestMoveToPublicSeaTime == null) return false;

        return time.after(latestMoveToPublicSeaTime);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date latestMoveToPublicSeaTime = targetCustomerDetail.getLatestMoveToPublicSeaTime();
        if (latestMoveToPublicSeaTime == null) return false;

        return time.before(latestMoveToPublicSeaTime);
    }
}
