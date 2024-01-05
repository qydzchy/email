package com.ruoyi.email.service.handler.email.column;

import com.ruoyi.common.enums.email.EmailColumnEnum;
import com.ruoyi.email.domain.bo.EmailSimpleBO;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;

/**
 * 邮件服务接口
 * @author tangJM.
 * @date 2021/10/13
 * @description
 */
public interface IColumnService {

    EmailColumnEnum getEmailColumnEnum();

    /**
     * 数据处理
     * @return
     */
    boolean handler(ExecuteConditionContentBO executeConditionContentBO, EmailSimpleBO emailSimpleBO);
}

