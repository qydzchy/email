package com.ruoyi.email.service.handler.email.column;

import com.ruoyi.common.enums.email.ConditionTypeEnum;
import com.ruoyi.common.enums.email.EmailColumnEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.bo.EmailSimpleBO;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import com.ruoyi.email.mapper.TaskEmailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发送人
 */
@Slf4j
@Component
public class FromerColumn implements IColumnService {

    @Resource
    private TaskEmailMapper taskEmailMapper;

    @Override
    public EmailColumnEnum getEmailColumnEnum() {
        return EmailColumnEnum.FROMER;
    }

    @Override
    public boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO) {
        Integer conditionType = executeConditionContentBO.getConditionType();
        if (conditionType == null) return false;
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getEnum(conditionType);
        if (conditionTypeEnum == null) return false;

        String fromer = emailSimpleBO.getFromer();
        if (StringUtils.isBlank(fromer)) return false;

        //1.包含 2.不包含 3.隶属于 4.等于 5.不等于
        String value = executeConditionContentBO.getValue();
        if (StringUtils.isBlank(value)) return false;
        value = value.trim();

        switch (conditionTypeEnum) {
            case INCLUDE:
                return fromer.contains(value);
            case NOT_INCLUDE:
                return !fromer.contains(value);
            case BELONGS_TO:
                // 私海/公海类型 1.私海 2.公海
                if (value.equals("1")) {
                    Long packetId = executeConditionContentBO.getPacketId();
                    int count = taskEmailMapper.countCustomerContactByFromer(1, fromer, packetId);
                    return count > 0;

                } else if (value.equals("2")) {
                    int count = taskEmailMapper.countCustomerContactByFromer(2, fromer, null);
                    return count > 0;

                }
            case EQUALS:
                return fromer.equals(value);
            case NOT_EQUALS:
                return !fromer.equals(value);
        }

        return false;
    }
}
