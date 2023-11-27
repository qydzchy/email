package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.service.handler.customer.column.utils.ColumnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 国家地区
 */
@Slf4j
@Component
public class CountryRegionColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.COUNTRY_REGION;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        String countryRegion = customerDetail.getCountryRegion();
        try {
            Integer conditionType = segmentConditionRule.getConditionType();
            ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
            Object value = segmentConditionRule.getValue();
            switch (conditionTypeEnum) {
                case IS_NULL:
                    return StringUtils.isBlank(countryRegion);

                case NOT_NULL:
                    return StringUtils.isNotBlank(countryRegion);

                case EQUALS:
                    if (StringUtils.isBlank(countryRegion)) return false;
                    return countryRegion.startsWith(String.valueOf(value));

                case NOT_EQUALS:
                    if (StringUtils.isBlank(countryRegion)) return false;
                    return !countryRegion.startsWith(String.valueOf(value));

                case IN:
                    if (StringUtils.isBlank(countryRegion)) return false;
                    List<String> selectNames = ColumnUtils.objectToList(value, String.class);
                    boolean exist = false;
                    for (String selectName : selectNames) {
                        exist = countryRegion.startsWith(selectName);
                        if (exist) break;
                    }

                    return exist;

                case NOT_IN:
                    if (StringUtils.isBlank(countryRegion)) return false;
                    List<String> selectNames2 = ColumnUtils.objectToList(value, String.class);
                    boolean exist2 = false;
                    for (String selectName : selectNames2) {
                        exist2 = countryRegion.startsWith(selectName);
                        if (exist2) break;
                    }

                    return !exist2;

                default:
                    return false;
            }
        } catch (Exception e) {
            log.error("国家地区处理异常", e);
            return false;
        }
    }
}
