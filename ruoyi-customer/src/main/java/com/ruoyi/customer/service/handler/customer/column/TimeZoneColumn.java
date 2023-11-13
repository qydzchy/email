package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 时区
 */
@Slf4j
@Component
public class TimeZoneColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.TIMEZONE;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        String timezone = customerDetail.getTimezone();
        try {
            return super.inNullStringHandler(timezone, segmentConditionRule);
        } catch (Exception e) {
            log.error("时区处理异常", e);
            return false;
        }
    }
}
