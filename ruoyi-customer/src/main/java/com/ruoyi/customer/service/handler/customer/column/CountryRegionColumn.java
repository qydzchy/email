package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


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
            return super.inNullStringHandler(countryRegion, segmentConditionRule);
        } catch (Exception e) {
            log.error("国家地区处理异常", e);
            return false;
        }
    }
}
