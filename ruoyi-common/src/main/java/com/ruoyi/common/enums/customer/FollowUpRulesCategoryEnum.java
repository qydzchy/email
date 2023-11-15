package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum FollowUpRulesCategoryEnum {

    LAST_CONTACTED_AT(1),
    LAST_FOLLOWUP_AT(2);

    private int category;
}
