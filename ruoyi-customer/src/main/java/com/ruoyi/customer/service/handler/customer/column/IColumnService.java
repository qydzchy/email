package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;

/**
 * 邮件服务接口
 * @author tangJM.
 * @date 2021/10/13
 * @description
 */
public interface IColumnService {

    CustomerColumnEnum getCustomerColumnEnum();

    /**
     * 数据处理
     * @param customerDetail
     * @param segmentConditionRule
     * @return
     */
    boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule);
}

