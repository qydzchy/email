package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.service.IUserDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 创建人
 */
@Slf4j
@Component
public class CreatorColumn extends ColumnAbstract implements IColumnService {

    @Resource
    private IUserDeptService userDeptService;

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.CREATOR;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        Long createId = customerDetail.getCreateId();

        try {
            Long deptId = userDeptService.getDeptIdByUserId(createId);
            UserDeptInfoBO userDeptInfoBO = UserDeptInfoBO.builder().userId(createId).deptId(deptId).build();
            return super.userHandler(userDeptInfoBO, segmentConditionRule);
        } catch (Exception e) {
            log.error("创建人处理异常", e);
            return false;
        }
    }
}
