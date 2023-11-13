package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

/**
 * 客户编号
 */
@Component
public class CustomerNoColumn extends ColumnAbstract implements IColumnService {
    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CUSTOMER_NO;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        return super.nullHandler(customerDetail.getCustomerNo(), segmentConditionRule);
    }
}
