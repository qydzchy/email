package com.ruoyi.customer.service.handler.customer.column;

import com.alibaba.fastjson2.util.DateUtils;
import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.common.enums.customer.DateTypeEnum;
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
        try {
            Integer conditionType = segmentConditionRule.getConditionType();
            ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
            switch (conditionTypeEnum) {
                case EQUALS:
                    Integer dateType = segmentConditionRule.getDateType();
                    if (dateType.intValue() == DateTypeEnum.SPECIFIC_DATE_RANGE.getType()) {
                        Object value = segmentConditionRule.getValue();
                        String[] dateArr = value.toString().split("~");
                        Date startDate = DateUtils.parseDate(dateArr[0]);
                        Date endDate = DateUtils.parseDate(dateArr[1]);
                        return createTime.compareTo(startDate) >= 0 && createTime.compareTo(endDate) <= 0;
                    } else if (dateType.intValue() == DateTypeEnum.DYNAMIC_DATE_RANGE.getType()) {
                        Object value = segmentConditionRule.getValue();
                        String[] dateArr = value.toString().split("~");
                        Date startDate = DateUtils.parseDate(dateArr[0]);
                        Date endDate = DateUtils.parseDate(dateArr[1]);
                        return createTime.compareTo(startDate) >= 0 && createTime.compareTo(endDate) <= 0;
                    }
                    break;
                case NOT_EQUALS:
                    Integer dateType2 = segmentConditionRule.getDateType();
                    if (dateType2.intValue() == DateTypeEnum.SPECIFIC_DATE_RANGE.getType()) {
                        Object value = segmentConditionRule.getValue();
                        String[] dateArr = value.toString().split("~");
                        Date startDate = DateUtils.parseDate(dateArr[0]);
                        Date endDate = DateUtils.parseDate(dateArr[1]);
                        return createTime.compareTo(startDate) >= 0 && createTime.compareTo(endDate) <= 0;
                    }
                    break;
                case IS_NULL:
                    break;
                case NOT_NULL:
                    break;
                case BEFORE:
                    break;
                case AFTER:
                    break;
            }
        } catch (Exception e) {
            log.error("创建时间处理异常", e);
            return false;
        }

        return false;
    }
}
