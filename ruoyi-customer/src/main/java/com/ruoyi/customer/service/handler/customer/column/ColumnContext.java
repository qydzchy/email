package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ColumnContext {

    private Map<CustomerColumnEnum, IColumnService> customerColumnMap = new HashMap<>();

    @Autowired
    public void setCustomerColumnMap(List<IColumnService> customerColumnServiceList){
        customerColumnServiceList.forEach((customerColumnService) -> {
            customerColumnMap.put(customerColumnService.getCustomerColumnEnum(), customerColumnService);
        });
    }

    /**
     * 数据处理
     */
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        String columnName = segmentConditionRule.getColumnName();
        CustomerColumnEnum customerColumnEnum = CustomerColumnEnum.getEnum(columnName);
        if (customerColumnEnum == null) return false;

        IColumnService customerColumnService = customerColumnMap.get(customerColumnEnum);
        if (customerColumnService == null) return false;

        return customerColumnService.handler(customerDetail, segmentConditionRule);
    }
}
