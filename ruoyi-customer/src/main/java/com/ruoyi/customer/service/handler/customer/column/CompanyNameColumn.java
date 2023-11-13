package com.ruoyi.customer.service.handler.customer.column;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import org.springframework.stereotype.Component;

/**
 * 公司名称
 */
@Component
public class CompanyNameColumn extends ColumnAbstract implements IColumnService {
    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.COMPANY_NAME;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        return super.nullHandler(customerDetail.getCompanyName(), segmentConditionRule);
    }
}
