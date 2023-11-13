package com.ruoyi.customer.service.handler.customer.column;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.bo.SegmentVisibilityScopeBO;
import com.ruoyi.customer.domain.bo.SegmentVisibilityScopeDeptBO;
import com.ruoyi.customer.domain.bo.SegmentVisibilityScopeUserBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpPersonnelListVO;
import com.ruoyi.customer.domain.vo.CustomerTagListVO;
import com.ruoyi.customer.service.handler.customer.column.utils.ColumnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        // todo 未完成

        /*if (tagList == null)  tagList = Collections.emptyList();

        List<Long> customerTagIds = tagList.stream().map(CustomerTagListVO::getId).collect(Collectors.toList());
        try {
            Integer conditionType = segmentConditionRule.getConditionType();
            ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
            Object value = segmentConditionRule.getValue();
            switch (conditionTypeEnum) {
                case IS_NULL:
                    return customerTagIds.isEmpty();

                case NOT_NULL:
                    return !customerTagIds.isEmpty();

                case EQUALS:
                    if (value == null) return false;
                    Long tagId = Long.parseLong(String.valueOf(value));
                    return customerTagIds.contains(tagId);

                case NOT_EQUALS:
                    if (value == null) return false;
                    Long tagId2 = Long.parseLong(String.valueOf(value));
                    return !customerTagIds.contains(tagId2);

                case IN:
                    if (customerTagIds == null) return false;
                    List<Long> tagIds = ColumnUtils.objectToList(value, Long.class);
                    for (Long customerId : customerTagIds) {
                        if (tagIds.contains(customerId)) {
                            return true;
                        }
                    }

                case NOT_IN:
                    if (customerTagIds == null) return false;
                    List<Long> tagIds2 = ColumnUtils.objectToList(value, Long.class);
                    boolean flag = false;
                    for (Long customerId : customerTagIds) {
                        if (tagIds2.contains(customerId)) {
                            flag = true;
                            break;
                        }
                    }
                    return !flag;

                default:
                    return false;
            }
        } catch (Exception e) {
            log.error("跟进人处理异常", e);
            return false;
        }*/

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
