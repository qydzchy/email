package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 下次移入公海日期
 */
@Slf4j
@Component
public class NextPublicleadsDateColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.NEXT_PUBLICLEADS_DATE;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Date latestMoveToPublicSeaTime = customerDetail.getLatestMoveToPublicSeaTime();
        if (latestMoveToPublicSeaTime == null) return false;

        try {
            return super.dateHandler(latestMoveToPublicSeaTime, customerDetail, segmentConditionRule);
        } catch (Exception e) {
            log.error("下次移入公海日期处理异常", e);
            return false;
        }
    }
}
