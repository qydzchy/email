package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.SourceListVO;
import com.ruoyi.customer.service.handler.customer.column.utils.ColumnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户来源
 */
@Slf4j
@Component
public class CustomerSourceColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CUSTOMER_SOURCE;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        List<SourceListVO> sourceList = customerDetail.getSourceList();
        if (sourceList == null) sourceList = Collections.emptyList();

        List<Long> customerSourceIds = sourceList.stream().map(SourceListVO::getId).collect(Collectors.toList());
        try {
            Integer conditionType = segmentConditionRule.getConditionType();
            ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
            Object value = segmentConditionRule.getValue();
            switch (conditionTypeEnum) {
                case IS_NULL:
                    return customerSourceIds.isEmpty();

                case NOT_NULL:
                    return !customerSourceIds.isEmpty();

                case EQUALS:
                    if (value == null) return false;
                    Long sourceId = Long.parseLong(String.valueOf(value));
                    return customerSourceIds.contains(sourceId);

                case NOT_EQUALS:
                    if (value == null) return false;
                    Long sourceId2 = Long.parseLong(String.valueOf(value));
                    return !customerSourceIds.contains(sourceId2);

                case IN:
                    if (value == null) return false;
                    List<Long> tagIds = super.convertDoubleListToLongList(ColumnUtils.objectToList(value, Double.class));
                    for (Long customerId : customerSourceIds) {
                        if (tagIds.contains(customerId)) {
                            return true;
                        }
                    }

                case NOT_IN:
                    if (value == null) return false;
                    List<Long> sourceIds2 = super.convertDoubleListToLongList(ColumnUtils.objectToList(value, Double.class));
                    boolean flag = false;
                    for (Long customerId : customerSourceIds) {
                        if (sourceIds2.contains(customerId)) {
                            flag = true;
                            break;
                        }
                    }
                    return !flag;

                default:
                    return false;
            }
        } catch (Exception e) {
            log.error("客户来源处理异常", e);
            return false;
        }
    }
}
