package com.ruoyi.customer.service.handler.customer.column.time;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpRecordsListVO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 最近[写跟进]时间
 */
@Component
public class LastFollowUpDateColumn implements ITimeColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_FOLLOW_UP_DATE;
    }

    @Override
    public boolean after(Date time, CustomerDetailVO targetCustomerDetail) {
        Date createTime = getTime(targetCustomerDetail);
        if (createTime == null) return false;

        return time.after(createTime);
    }

    @Override
    public boolean before(Date time, CustomerDetailVO targetCustomerDetail) {
        Date createTime = getTime(targetCustomerDetail);
        if (createTime == null) return false;

        return time.before(createTime);
    }


    private Date getTime(CustomerDetailVO customerDetail) {
        List<CustomerFollowUpRecordsListVO> followUpRecordsList = customerDetail.getFollowUpRecordsList();
        Date createTime = null;
        if (followUpRecordsList == null || followUpRecordsList.isEmpty()) {
            CustomerFollowUpRecordsListVO customerFollowUpRecordsVO = followUpRecordsList.get(0);
            if (StringUtils.isNotBlank(customerFollowUpRecordsVO.getOperatorTime())) {
                try {
                    createTime = DateUtils.parseDate(customerFollowUpRecordsVO.getOperatorTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
                } catch (ParseException e) {}
            }
        }

        return createTime;
    }
}
