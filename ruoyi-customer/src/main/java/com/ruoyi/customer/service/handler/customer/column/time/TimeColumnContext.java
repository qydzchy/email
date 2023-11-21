package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimeColumnContext {

    /**
     * 晚于
     *
     * @param columnName
     * @param time
     * @param targetCustomerDetail
     * @return
     */
    public boolean after(String columnName, Date time, CustomerDetailVO targetCustomerDetail) {
        ITimeColumnService timeColumnService = getTimeColumnService(columnName);
        if (timeColumnService == null) return false;

        return timeColumnService.after(time, targetCustomerDetail);
    }

    /**
     * 早于
     * @param columnName
     * @param time
     * @param targetCustomerDetail
     * @return
     */
    public boolean before(String columnName, Date time, CustomerDetailVO targetCustomerDetail) {
        ITimeColumnService timeColumnService = getTimeColumnService(columnName);
        if (timeColumnService == null) return false;

        return timeColumnService.before(time, targetCustomerDetail);
    }


    private ITimeColumnService getTimeColumnService(String columnName) {
        CustomerColumnEnum customerColumnEnum = CustomerColumnEnum.getEnum(columnName);
        ITimeColumnService timeColumnService = null;
        switch (customerColumnEnum) {
            case CREATE_TIME:
                timeColumnService = new CreateTimeColumn();
                break;
            case DATA_UPDATE_TIME:
                timeColumnService = new DataUpdateTimeColumn();
                break;
            case LAST_CONTACTED_AT:
                timeColumnService = new LastContactedAtColumn();
                break;
            case LAST_FOLLOWUP_AT:
                timeColumnService = new LastFollowupAtColumn();
                break;
            case LAST_FOLLOW_UP_DATE:
                timeColumnService = new LastFollowUpDateColumn();
                break;
            case LAST_PRIVATELEADS_ENTRY:
                timeColumnService = new LastPrivateleadsEntryColumn();
                break;
            case LAST_PUBLICLEADS_ENTRY:
                timeColumnService = new LastPublicleadsEntryColumn();
                break;
            case NEXT_PUBLICLEADS_DATE:
                timeColumnService = new NextPublicleadsDateColumn();
                break;
        }

        return timeColumnService;
    }
}
