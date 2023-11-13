package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 公海分组
 */
@Slf4j
@Component
public class PublicleadsGroupsColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.PUBLICLEADS_GROUPS;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        PublicleadsGroupsListVO publicleadsGroups = customerDetail.getPublicleadsGroups();
        Long publicleadsGroupsId = publicleadsGroups != null ? publicleadsGroups.getId() : null;

        try {
            return super.inNullLongHandler(publicleadsGroupsId, segmentConditionRule);
        } catch (Exception e) {
            log.error("客户公海分组处理异常", e);
            return false;
        }
    }
}
