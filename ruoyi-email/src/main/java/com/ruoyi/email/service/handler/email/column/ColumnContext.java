package com.ruoyi.email.service.handler.email.column;

import com.ruoyi.common.enums.email.EmailColumnEnum;
import com.ruoyi.email.domain.bo.EmailSimpleBO;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ColumnContext {

    private Map<EmailColumnEnum, IColumnService> emailColumnMap = new HashMap<>();

    @Autowired
    public void setCustomerColumnMap(List<IColumnService> emailColumnServiceList){
        emailColumnServiceList.forEach((emailColumnService) -> {
            emailColumnMap.put(emailColumnService.getEmailColumnEnum(), emailColumnService);
        });
    }

    /**
     * 数据处理
     */
    public boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO) {
        String columnName = executeConditionContentBO.getColumnName();
        EmailColumnEnum emailColumnEnum = EmailColumnEnum.getEnum(columnName);
        if (emailColumnEnum == null) return false;

        IColumnService customerColumnService = emailColumnMap.get(emailColumnEnum);
        if (customerColumnService == null) return false;

        return customerColumnService.handler(executeConditionContentBO, emailSimpleBO);
    }
}
