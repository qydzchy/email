package com.ruoyi.email.service.handler.email.column;

import com.ruoyi.common.enums.email.ConditionTypeEnum;
import com.ruoyi.common.enums.email.EmailColumnEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.bo.EmailSimpleBO;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 主题
 */
@Slf4j
@Component
public class SubjectColumn implements IColumnService {
    @Override
    public EmailColumnEnum getEmailColumnEnum() {
        return EmailColumnEnum.SUBJECT;
    }

    @Override
    public boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO) {
        Integer conditionType = executeConditionContentBO.getConditionType();
        if (conditionType == null) return false;
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getEnum(conditionType);
        if (conditionTypeEnum == null) return false;

        String subject = emailSimpleBO.getSubject();
        if (StringUtils.isBlank(subject)) return false;

        //1.包含 2.不包含 3.隶属于 4.等于 5.不等于
        String value = executeConditionContentBO.getValue();
        if (StringUtils.isBlank(value)) return false;
        value = value.trim();

        switch (conditionTypeEnum) {
            case INCLUDE:
                return subject.contains(value);
            case NOT_INCLUDE:
                return !subject.contains(value);
        }

        return false;
    }
}
