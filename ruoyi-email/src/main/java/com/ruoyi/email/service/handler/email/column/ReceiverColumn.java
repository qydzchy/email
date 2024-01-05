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
 * 收件人
 */
@Slf4j
@Component
public class ReceiverColumn implements IColumnService {
    @Override
    public EmailColumnEnum getEmailColumnEnum() {
        return EmailColumnEnum.RECEIVER;
    }

    @Override
    public boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO) {
        Integer conditionType = executeConditionContentBO.getConditionType();
        if (conditionType == null) return false;
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getEnum(conditionType);
        if (conditionTypeEnum == null) return false;

        String receiver = emailSimpleBO.getReceiver();
        if (StringUtils.isBlank(receiver)) return false;

        List<String> receiverEmailList = getReceiverEmail(receiver);
        if (receiverEmailList.isEmpty()) return false;

        //1.包含 2.不包含 3.隶属于 4.等于 5.不等于
        String value = executeConditionContentBO.getValue();
        if (StringUtils.isBlank(value)) return false;
        value = value.trim();

        for (String receiverEmail : receiverEmailList) {
            switch (conditionTypeEnum) {
                case INCLUDE:
                    return receiverEmail.contains(value);
                case NOT_INCLUDE:
                    return !receiverEmail.contains(value);
                case EQUALS:
                    return receiverEmail.equals(value);
                case NOT_EQUALS:
                    return !receiverEmail.equals(value);
            }
        }

        return false;
    }


    private List<String> getReceiverEmail(String receiver) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(receiver);
            if (jsonArray == null || jsonArray.isEmpty()) return Collections.emptyList();

            List<String> list = Collections.emptyList();
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String email = jsonObject.getString("email");
                list.add(email);
            }
            return list;

        } catch (Exception e) {
            log.error("getReceiverEmail error", e);
        }

        return Collections.emptyList();
    }
}
