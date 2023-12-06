package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 创建时间
 */
@Slf4j
@Component
public class CreateTimeColumn extends ColumnAbstract implements IColumnService {
    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CREATE_TIME;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Date createTime = customerDetail.getCreateTime();
        if (createTime == null) return false;
        try {
            return super.dateHandler(createTime, customerDetail, segmentConditionRule);
        } catch (Exception e) {
            log.error("创建时间处理异常", e);
            return false;
        }
    }
}
