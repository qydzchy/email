package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 最近联系时间
 */
@Slf4j
@Component
public class LastContactedAtColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_CONTACTED_AT;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Date lastContactedAt = customerDetail.getLastContactedAt();
        if (lastContactedAt == null) return false;
        try {
            return super.dateHandler(lastContactedAt, segmentConditionRule);
        } catch (Exception e) {
            log.error("最近联系时间处理异常", e);
            return false;
        }
    }
}
