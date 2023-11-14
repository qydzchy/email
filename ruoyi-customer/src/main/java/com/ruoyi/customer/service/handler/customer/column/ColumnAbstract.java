package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.ConditionTypeEnum;
import com.ruoyi.common.enums.customer.DateTypeEnum;
import com.ruoyi.common.enums.customer.TimeRangeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.service.handler.customer.column.utils.ColumnUtils;
import com.ruoyi.customer.service.handler.customer.column.utils.TimeRangeUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.util.Pair;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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
     * 日期时间处理
     * @return
     */
    protected boolean dateHandler(Date time, SegmentConditionRuleBO segmentConditionRule) throws Exception {
        Integer conditionType = segmentConditionRule.getConditionType();
        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getByType(conditionType);
        switch (conditionTypeEnum) {
            case EQUALS:
                return equalsTime(time, segmentConditionRule);

            case NOT_EQUALS:
                return !equalsTime(time, segmentConditionRule);

            case IS_NULL:
                return time == null;

            case NOT_NULL:
                return time != null;

            case BEFORE:
                return beforeTime(time, segmentConditionRule);

            case AFTER:
                return afterTime(time, segmentConditionRule);
        }

        return false;
    }


    /**
     * 晚于多少钱
     * @param time
     * @param segmentConditionRule
     * @return
     */
    private boolean afterTime(Date time, SegmentConditionRuleBO segmentConditionRule) throws ParseException {
        Integer timeRange = segmentConditionRule.getTimeRange();
        TimeRangeEnum timeRangeEnum = TimeRangeEnum.getByCode(timeRange);
        switch (timeRangeEnum) {
            case BEFORE_TODAY:
                Date today1 = new Date();
                int value1 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                Date laterDateBeforeSpecifiedDate = calculateDateBefore(today1, value1);
                return laterDateBeforeSpecifiedDate.before(time);

            case AFTER_TODAY:
                Date today2 = new Date();
                int value2 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                Date laterDateAfterSpecifiedDate = calculateDateAfter(today2, value2);
                return laterDateAfterSpecifiedDate.after(time);

            case SPECIFIC_DATE:
                String value = String.valueOf(segmentConditionRule.getValue());
                Date specifiedDateTime = DateUtils.parseDate(value + " 00:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS);
                return time.after(specifiedDateTime);

            // todo 未完成
            case DATE_FIELD:
                break;
        }

        return false;
    }

    /**
     * 早于多少天
     * @param time
     * @param segmentConditionRule
     * @return
     */
    private boolean beforeTime(Date time, SegmentConditionRuleBO segmentConditionRule) throws Exception {
        Integer timeRange = segmentConditionRule.getTimeRange();
        TimeRangeEnum timeRangeEnum = TimeRangeEnum.getByCode(timeRange);
        switch (timeRangeEnum) {
            case BEFORE_TODAY:
                Date today1 = new Date();
                int value1 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                Date earlierDateBeforeToday = calculateDateBefore(today1, value1);
                return earlierDateBeforeToday.before(time);

            case AFTER_TODAY:
                Date today2 = new Date();
                int value2 = Integer.parseInt(String.valueOf(segmentConditionRule.getValue()));
                Date earlierDateAfterToday = calculateDateAfter(today2, value2);
                return earlierDateAfterToday.after(time);

            case SPECIFIC_DATE:
                String value = String.valueOf(segmentConditionRule.getValue());
                Date specifiedDateTime = DateUtils.parseDate(value + " 00:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS);
                return time.before(specifiedDateTime);

                // todo 未完成
            case DATE_FIELD:
                break;
        }

        return false;
    }


    // 计算相对于给定日期的早于之前的新日期
    private static Date calculateDateBefore(Date date, int daysBefore) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -daysBefore);
        return calendar.getTime();
    }

    // 计算相对于给定日期的早于之后的新日期
    private static Date calculateDateAfter(Date date, int daysAfter) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysAfter);
        return calendar.getTime();
    }

    /**
     * 比较时间
     * @param time
     * @param segmentConditionRule
     * @return
     * @throws Exception
     */
    private boolean equalsTime(Date time, SegmentConditionRuleBO segmentConditionRule) throws Exception {
        Integer dateType = segmentConditionRule.getDateType();
        if (dateType.intValue() == DateTypeEnum.SPECIFIC_DATE_RANGE.getType()) {
            Object value = segmentConditionRule.getValue();
            String[] dateArr = value.toString().split("~");
            Date startTime = DateUtils.parseDate(dateArr[0] + " 00:00:00");
            Date endTime = DateUtils.parseDate(dateArr[1] + " 23:59:59");
            return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
        } else if (dateType.intValue() == DateTypeEnum.DYNAMIC_DATE_RANGE.getType()) {
            Integer timeRange = segmentConditionRule.getTimeRange();
            TimeRangeEnum timeRangeEnum = TimeRangeEnum.getByCode(timeRange);
            Pair<Date, Date> timeRangePair = getTimeRange(timeRangeEnum);
            Date startTime = timeRangePair.getFirst();
            Date endTime = timeRangePair.getSecond();
            return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
        }

        return false;
    }


    /**
     * 获取时间范围的开始时间和结束时间
     * @param timeRangeEnum
     * @return
     */
    private Pair<Date, Date> getTimeRange(TimeRangeEnum timeRangeEnum) {
        Date startTime = null;
        Date endTime = null;
        // todo 待补齐
        switch (timeRangeEnum) {
            case TODAY:
                startTime = TimeRangeUtils.getStartOfDay(new Date());
                endTime = TimeRangeUtils.getEndOfDay(new Date());
                break;
            case YESTERDAY:
                startTime = TimeRangeUtils.getStartOfYesterday();
                endTime = TimeRangeUtils.getEndOfYesterday();
                break;
            case TOMORROW:
                startTime = TimeRangeUtils.getStartOfTomorrow();
                endTime = TimeRangeUtils.getEndOfTomorrow();
                break;
            case THIS_WEEK:
                startTime = TimeRangeUtils.getStartOfThisWeek();
                endTime = TimeRangeUtils.getEndOfThisWeek();
                break;
            case LAST_WEEK:
                startTime = TimeRangeUtils.getStartOfLastWeek();
                endTime = TimeRangeUtils.getEndOfLastWeek();
                break;
            case NEXT_WEEK:
                startTime = TimeRangeUtils.getStartOfNextWeek();
                endTime  = TimeRangeUtils.getEndOfNextWeek();
                break;
        }

        return Pair.of(startTime, endTime);
    }


}
