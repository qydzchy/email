package com.ruoyi.customer.service.handler.customer.column;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.*;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpPersonnelListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 跟进人
 */
@Slf4j
@Component
public class FollowUpPersonnelColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.FOLLOW_UP_PERSONNEL;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        List<CustomerFollowUpPersonnelListVO> followUpPersonnelList = customerDetail.getFollowUpPersonnelList();
        try {
            for (CustomerFollowUpPersonnelListVO followUpPersonnel : followUpPersonnelList) {
                UserDeptInfoBO userDeptInfoBO = UserDeptInfoBO.builder().userId(followUpPersonnel.getUserId()).deptId(followUpPersonnel.getDeptId()).build();
                if (super.userHandler(userDeptInfoBO, segmentConditionRule)) return true;
            }

        } catch (Exception e) {
            log.error("跟进人处理异常", e);
            return false;
        }

        return false;
    }

    /**
     * 获取选中人员
     * @param content
     */
    private SegmentVisibilityScopeBO getSelectFollowUpPersonnel(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 字符串转换为 Java 对象
        try {
            SegmentVisibilityScopeBO segmentVisibilityScopeBO = objectMapper.readValue(content, SegmentVisibilityScopeBO.class);
            return segmentVisibilityScopeBO;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
