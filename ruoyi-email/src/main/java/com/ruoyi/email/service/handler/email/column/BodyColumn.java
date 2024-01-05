package com.ruoyi.email.service.handler.email.column;

import com.ruoyi.common.enums.email.ConditionTypeEnum;
import com.ruoyi.common.enums.email.EmailColumnEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.bo.EmailSimpleBO;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 正文
 */
@Slf4j
@Component
public class BodyColumn implements IColumnService {
    @Override
    public EmailColumnEnum getEmailColumnEnum() {
        return EmailColumnEnum.BODY;
    }

    @Override
    public boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO) {
        Integer conditionType = executeConditionContentBO.getConditionType();
        if (conditionType == null) return false;
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getEnum(conditionType);
        if (conditionTypeEnum == null) return false;

        String body = emailSimpleBO.getBody();
        if (StringUtils.isBlank(body)) return false;

        //1.包含 2.不包含 3.隶属于 4.等于 5.不等于
        String value = executeConditionContentBO.getValue();
        if (StringUtils.isBlank(value)) return false;
        value = value.trim();

        switch (conditionTypeEnum) {
            case INCLUDE:
                return body.contains(value);
            case NOT_INCLUDE:
                return !body.contains(value);
        }

        return false;
    }
}
