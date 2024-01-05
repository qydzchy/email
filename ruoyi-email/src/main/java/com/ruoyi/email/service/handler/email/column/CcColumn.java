package com.ruoyi.email.service.handler.email.column;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.email.ConditionTypeEnum;
import com.ruoyi.common.enums.email.EmailColumnEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.bo.EmailSimpleBO;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 抄送人
 */
@Slf4j
@Component
public class CcColumn implements IColumnService {

    @Override
    public EmailColumnEnum getEmailColumnEnum() {
        return EmailColumnEnum.CC;
    }

    @Override
    public boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO) {
        Integer conditionType = executeConditionContentBO.getConditionType();
        if (conditionType == null) return false;
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getEnum(conditionType);
        if (conditionTypeEnum == null) return false;

        String cc = emailSimpleBO.getCc();
        if (StringUtils.isBlank(cc)) return false;

        List<String> ccEmailList = getCcEmail(cc);
        if (ccEmailList.isEmpty()) return false;

        //1.包含 2.不包含 3.隶属于 4.等于 5.不等于
        String value = executeConditionContentBO.getValue();
        if (StringUtils.isBlank(value)) return false;
        value = value.trim();

        for (String ccEmail : ccEmailList) {
            switch (conditionTypeEnum) {
                case INCLUDE:
                    return ccEmail.contains(value);
                case NOT_INCLUDE:
                    return !ccEmail.contains(value);
                case EQUALS:
                    return ccEmail.equals(value);
                case NOT_EQUALS:
                    return !ccEmail.equals(value);
            }
        }

        return false;
    }


    private List<String> getCcEmail(String cc) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(cc);
            if (jsonArray == null || jsonArray.isEmpty()) return Collections.emptyList();

            List<String> list = Collections.emptyList();
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String email = jsonObject.getString("email");
                list.add(email);
            }
            return list;

        } catch (Exception e) {
            log.error("getCcEmail error", e);
        }

        return Collections.emptyList();
    }
}
