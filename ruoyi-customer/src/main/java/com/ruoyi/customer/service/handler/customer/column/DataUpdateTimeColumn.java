package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 资料更新时间
 */
@Slf4j
@Component
public class DataUpdateTimeColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.DATA_UPDATE_TIME;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Date updateTime = customerDetail.getUpdateTime();
        if (updateTime == null) return false;
        try {
            return super.dateHandler(updateTime, segmentConditionRule);
        } catch (Exception e) {
            log.error("资料更新时间处理异常", e);
            return false;
        }
    }
}
