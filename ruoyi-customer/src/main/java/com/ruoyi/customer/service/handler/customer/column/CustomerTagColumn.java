package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerTagListVO;
import com.ruoyi.customer.service.handler.customer.column.utils.ColumnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户标签
 */
@Slf4j
@Component
public class CustomerTagColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CUSTOMER_TAG;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        List<CustomerTagListVO> tagList = customerDetail.getTagList();
        if (tagList == null)  tagList = Collections.emptyList();

        List<Long> customerTagIds = tagList.stream().map(CustomerTagListVO::getId).collect(Collectors.toList());
        try {
            Integer conditionType = segmentConditionRule.getConditionType();
            ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
            Object value = segmentConditionRule.getValue();
            switch (conditionTypeEnum) {
                case IS_NULL:
                    return customerTagIds.isEmpty();

                case NOT_NULL:
                    return !customerTagIds.isEmpty();

                case EQUALS:
                    if (value == null) return false;
                    Long tagId = Long.parseLong(String.valueOf(value));
                    return customerTagIds.contains(tagId);

                case NOT_EQUALS:
                    if (value == null) return false;
                    Long tagId2 = Long.parseLong(String.valueOf(value));
                    return !customerTagIds.contains(tagId2);

                case IN:
                    if (customerTagIds == null) return false;
                    List<Long> tagIds = ColumnUtils.objectToList(value, Long.class);
                    for (Long customerId : customerTagIds) {
                        if (tagIds.contains(customerId)) {
                            return true;
                        }
                    }

                case NOT_IN:
                    if (customerTagIds == null) return false;
                    List<Long> tagIds2 = ColumnUtils.objectToList(value, Long.class);
                    boolean flag = false;
                    for (Long customerId : customerTagIds) {
                        if (tagIds2.contains(customerId)) {
                            flag = true;
                            break;
                        }
                    }
                    return !flag;

                default:
                    return false;
            }
        } catch (Exception e) {
            log.error("客户标签处理异常", e);
            return false;
        }
    }
}
