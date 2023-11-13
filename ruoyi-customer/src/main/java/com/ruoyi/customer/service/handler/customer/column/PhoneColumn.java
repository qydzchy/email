package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.CustomerContactBO;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 联系电话
 */
@Slf4j
@Component
public class PhoneColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.PHONE;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        List<CustomerContactBO> contactList = customerDetail.getContactList();
        if (contactList != null && !contactList.isEmpty()) {
            for (CustomerContactBO customerContactBO : contactList) {
                if (super.nullHandler(customerContactBO.getPhone(), segmentConditionRule)) {
                    return true;
                }
            }
        }
        return false;
    }
}
