package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.service.handler.customer.column.utils.ColumnUtils;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class ColumnAbstract {

    /**
     * 空值处理
     * @param value
     * @param segmentConditionRule
     * @return
     */
    protected boolean nullHandler(String value, SegmentConditionRuleBO segmentConditionRule) {
        Integer conditionType = segmentConditionRule.getConditionType();
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
        switch (conditionTypeEnum) {
            case IS_NULL:
                return StringUtils.isBlank(value);
            case NOT_NULL:
                return StringUtils.isNotBlank(value);
            default:
                return false;
        }
    }

    /**
     * 属于+空值处理（长整型（id））
     */
    protected boolean inNullLongHandler(Long relationId, SegmentConditionRuleBO segmentConditionRule) {
        Integer conditionType = segmentConditionRule.getConditionType();
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
        Object value = segmentConditionRule.getValue();
        switch (conditionTypeEnum) {
            case IS_NULL:
                return relationId == null;

            case NOT_NULL:
                return relationId != null;

            case IN:
                if (relationId == null) return false;
                List<Long> selectIds = ColumnUtils.objectToList(value, Long.class);
                return selectIds.contains(relationId);

            case NOT_IN:
                if (relationId == null) return false;
                List<Long> selectIds2 = ColumnUtils.objectToList(value, Long.class);
                return !selectIds2.contains(relationId);

            default:
                return false;
        }
    }

    /**
     * 属于+空值处理（字符串）
     */
    protected boolean inNullStringHandler(String name, SegmentConditionRuleBO segmentConditionRule) {
        Integer conditionType = segmentConditionRule.getConditionType();
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
        Object value = segmentConditionRule.getValue();
        switch (conditionTypeEnum) {
            case IS_NULL:
                return StringUtils.isBlank(name);

            case NOT_NULL:
                return StringUtils.isNotBlank(name);

            case IN:
                if (StringUtils.isBlank(name)) return false;
                List<String> selectNames = ColumnUtils.objectToList(value, String.class);
                return selectNames.contains(name);

            case NOT_IN:
                if (StringUtils.isBlank(name)) return false;
                List<String> selectNames2 = ColumnUtils.objectToList(value, String.class);
                return !selectNames2.contains(name);

            default:
                return false;
        }
    }

    /**
     * 属于+空值处理（整形（类型））
     */
    protected boolean inNullIntegerHandler(Integer type, SegmentConditionRuleBO segmentConditionRule) {
        Integer conditionType = segmentConditionRule.getConditionType();
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
        Object value = segmentConditionRule.getValue();
        switch (conditionTypeEnum) {
            case IS_NULL:
                return type == null;

            case NOT_NULL:
                return type != null;

            case IN:
                if (type == null) return false;
                List<Integer> selectTypes = ColumnUtils.objectToList(value, Integer.class);
                return selectTypes.contains(type);

            case NOT_IN:
                if (type == null) return false;
                List<Integer> selectTypes2 = ColumnUtils.objectToList(value, Integer.class);
                return !selectTypes2.contains(type);

            default:
                return false;
        }
    }

    /**
     * 等于+属于+空值处理
     */
    protected boolean equalsBelongsToNullHandler(List<T> itemList, SegmentConditionRuleBO segmentConditionRule) {
        return false;
    }


}
