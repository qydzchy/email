package com.ruoyi.email.domain.vo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
public class TransceiverRuleListVO {

    /** 主键 */
    private Long id;

    /** 规则类型: 1.收件规则 2.发件规则 */
    private Integer ruleType;

    /** 规则名称 */
    private String ruleName;

    /** 状态 1.开启 0.关闭 */
    private Integer status;

    /**
     * 执行规则
     */
    private String executionRule;
}
