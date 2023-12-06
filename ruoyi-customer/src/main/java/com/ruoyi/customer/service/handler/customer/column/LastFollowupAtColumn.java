package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 最近跟进时间
 */
@Slf4j
@Component
public class LastFollowupAtColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_FOLLOWUP_AT;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Date lastFollowupAt = customerDetail.getLastFollowupAt();
        if (lastFollowupAt == null) return false;
        try {
            return super.dateHandler(lastFollowupAt, customerDetail, segmentConditionRule);
        } catch (Exception e) {
            log.error("最近跟进时间处理异常", e);
            return false;
        }
    }
}
