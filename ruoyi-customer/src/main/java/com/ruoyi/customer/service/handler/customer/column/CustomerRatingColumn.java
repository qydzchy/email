package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 客户星级
 */
@Slf4j
@Component
public class CustomerRatingColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CUSTOMER_RATING;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Integer rating = customerDetail.getRating();

        try {
            return super.inNullIntegerHandler(rating, segmentConditionRule);
        } catch (Exception e) {
            log.error("客户星级处理异常", e);
            return false;
        }
    }
}
