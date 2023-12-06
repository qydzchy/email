package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimeColumnContext {

    private Map<CustomerColumnEnum, ITimeColumnService> timeColumnMap = new HashMap<>();

    @Autowired
    public void setCustomerColumnMap(List<ITimeColumnService> timeColumnServiceList){
        timeColumnServiceList.forEach((timeColumnService) -> {
            timeColumnMap.put(timeColumnService.getCustomerColumnEnum(), timeColumnService);
        });
    }

    /**
     * 晚于
     * @param columnName
     * @param time
     * @param targetCustomerDetail
     * @return
     */
    public boolean after(String columnName, Date time, CustomerDetailVO targetCustomerDetail) {
        CustomerColumnEnum customerColumnEnum = CustomerColumnEnum.getEnum(columnName);
        ITimeColumnService timeColumnService = timeColumnMap.get(customerColumnEnum);
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
        CustomerColumnEnum customerColumnEnum = CustomerColumnEnum.getEnum(columnName);
        ITimeColumnService timeColumnService = timeColumnMap.get(customerColumnEnum);
        return timeColumnService.before(time, targetCustomerDetail);
    }

}
