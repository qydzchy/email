package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 详细地址
 */
@Slf4j
@Component
public class AddressColumn extends ColumnAbstract implements IColumnService {
    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.ADDRESS;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        return super.nullHandler(customerDetail.getAddress(), segmentConditionRule);
    }
}
