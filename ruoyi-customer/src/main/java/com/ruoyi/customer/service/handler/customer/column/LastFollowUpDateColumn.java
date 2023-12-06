package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpRecordsListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 最近[写跟进]时间
 */
@Slf4j
@Component
public class LastFollowUpDateColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.LAST_FOLLOW_UP_DATE;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        List<CustomerFollowUpRecordsListVO> followUpRecordsList = customerDetail.getFollowUpRecordsList();
        try {
            Date createTime = null;
            if (followUpRecordsList == null || followUpRecordsList.isEmpty()) {
                CustomerFollowUpRecordsListVO customerFollowUpRecordsVO = followUpRecordsList.get(0);
                if (StringUtils.isNotBlank(customerFollowUpRecordsVO.getOperatorTime())) {
                    createTime = DateUtils.parseDate(customerFollowUpRecordsVO.getOperatorTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
                }
            }

            if (createTime == null) return false;

            return super.dateHandler(createTime, customerDetail, segmentConditionRule);
        } catch (Exception e) {
            log.error("最近[写跟进]时间处理异常", e);
            return false;
        }
    }
}
