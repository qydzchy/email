package com.ruoyi.customer.service.handler.customer.column.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间范围工具类
 */
public class TimeRangeUtils {

    // 获取一天的开始时间
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // 获取一天的结束时间
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    // 获取昨天的开始时间
    public static Date getStartOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取昨天的结束时间
    public static Date getEndOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return getEndOfDay(calendar.getTime());
    }

    // 获取明天的开始时间
    public static Date getStartOfTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取明天的结束时间
    public static Date getEndOfTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getEndOfDay(calendar.getTime());
    }

    // 获取本周的开始时间
    public static Date getStartOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return getStartOfDay(calendar.getTime());
    }

    // 获取本周的结束时间
    public static Date getEndOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return getEndOfDay(calendar.getTime());
    }

    // 获取上周的开始时间
    public static Date getStartOfLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return getStartOfDay(calendar.getTime());
    }

    // 获取上周的结束时间
    public static Date getEndOfLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return getEndOfDay(calendar.getTime());
    }

    // 获取下周的开始时间
    public static Date getStartOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return getStartOfDay(calendar.getTime());
    }

    // 获取下周的结束时间
    public static Date getEndOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return getEndOfDay(calendar.getTime());
    }

    // 获取本月的开始时间
    public static Date getStartOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取本月的结束时间
    public static Date getEndOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return getEndOfDay(calendar.getTime());
    }

    // 获取上月的开始时间
    public static Date getStartOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取上月的结束时间
    public static Date getEndOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return getEndOfDay(calendar.getTime());
    }

    // 获取本季度的开始时间
    public static Date getStartOfThisQuarter() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int startMonth = (currentMonth / 3) * 3; // Calculate the start month of the quarter
        calendar.set(Calendar.MONTH, startMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取本季度的结束时间
    public static Date getEndOfThisQuarter() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int endMonth = ((currentMonth / 3) + 1) * 3 - 1; // Calculate the end month of the quarter
        calendar.set(Calendar.MONTH, endMonth);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndOfDay(calendar.getTime());
    }

    // 获取上季度的开始时间
    public static Date getStartOfLastQuarter() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int startMonth = ((currentMonth / 3) - 1) * 3; // Calculate the start month of the last quarter
        calendar.set(Calendar.MONTH, startMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取上季度的结束时间
    public static Date getEndOfLastQuarter() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int endMonth = (currentMonth / 3) * 3 - 1; // Calculate the end month of the last quarter
        calendar.set(Calendar.MONTH, endMonth);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndOfDay(calendar.getTime());
    }

    // 获取本年的开始时间
    public static Date getStartOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取本年的结束时间
    public static Date getEndOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return getEndOfDay(calendar.getTime());
    }

    // 获取过去7天的开始时间
    public static Date getStartOfPast7Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去7天的结束时间
    public static Date getEndOfPast7Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取过去14天的开始时间
    public static Date getStartOfPast14Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -13);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去14天的结束时间
    public static Date getEndOfPast14Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取过去30天的开始时间
    public static Date getStartOfPast30Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -29);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去30天的结束时间
    public static Date getEndOfPast30Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取过去60天的开始时间
    public static Date getStartOfPast60Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -59);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去60天的结束时间
    public static Date getEndOfPast60Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取过去90天的开始时间
    public static Date getStartOfPast90Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -89);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去90天的结束时间
    public static Date getEndOfPast90Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取过去180天的开始时间
    public static Date getStartOfPast180Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -179);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去180天的结束时间
    public static Date getEndOfPast180Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取过去365天的开始时间
    public static Date getStartOfPast365Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -364);
        return getStartOfDay(calendar.getTime());
    }

    // 获取过去365天的结束时间
    public static Date getEndOfPast365Days() {
        return getEndOfDay(new Date()); // End time is the current time
    }

    // 获取未来3天的开始时间
    public static Date getStartOfNext3Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取未来3天的结束时间
    public static Date getEndOfNext3Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        return getEndOfDay(calendar.getTime());
    }

    // 获取未来7天的开始时间
    public static Date getStartOfNext7Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取未来7天的结束时间
    public static Date getEndOfNext7Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        return getEndOfDay(calendar.getTime());
    }

    // 获取未来14天的开始时间
    public static Date getStartOfNext14Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取未来14天的结束时间
    public static Date getEndOfNext14Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        return getEndOfDay(calendar.getTime());
    }

    // 获取未来30天的开始时间
    public static Date getStartOfNext30Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(calendar.getTime());
    }

    // 获取未来30天的结束时间
    public static Date getEndOfNext30Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return getEndOfDay(calendar.getTime());
    }
}
