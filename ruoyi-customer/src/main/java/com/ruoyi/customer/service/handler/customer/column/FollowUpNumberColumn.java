package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpPersonnelListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 跟进人数
 */
@Slf4j
@Component
public class FollowUpNumberColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.FOLLOW_UP_NUMBER;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        List<CustomerFollowUpPersonnelListVO> followUpPersonnelList = customerDetail.getFollowUpPersonnelList();
        int followUpNumber = followUpPersonnelList != null ? followUpPersonnelList.size() : 0;

        try {
            Integer conditionType = segmentConditionRule.getConditionType();
            ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
            switch (conditionTypeEnum) {
                case IS_NULL:
                    return followUpNumber == 0;

                case NOT_NULL:
                    return followUpNumber > 0;

                case EQUALS:
                    int v1 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                    return followUpNumber == v1;

                case NOT_EQUALS:
                    int v2 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                    return followUpNumber != v2;

                case GREATER_THAN:
                    int v3 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                    return followUpNumber > v3;

                case GREATER_THAN_OR_EQUAL:
                    int v4 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                    return followUpNumber >= v4;

                case LESS_THAN:
                    int v5 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                    return followUpNumber < v5;

                case LESS_THAN_OR_EQUAL:
                    int v6 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                    return followUpNumber <= v6;

                default:
                    return false;
            }
        } catch (Exception e) {
            log.error("客户阶段处理异常", e);
            return false;
        }
    }
}
