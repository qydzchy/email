package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.StageListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 客户阶段
 */
@Slf4j
@Component
public class CustomerStageColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CUSTOMER_STAGE;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        StageListVO stage = customerDetail.getStage();
        Long stageId = stage != null ? stage.getId() : null;

        try {
            return super.inNullLongHandler(stageId, segmentConditionRule);
        } catch (Exception e) {
            log.error("客户阶段处理异常", e);
            return false;
        }
    }
}
