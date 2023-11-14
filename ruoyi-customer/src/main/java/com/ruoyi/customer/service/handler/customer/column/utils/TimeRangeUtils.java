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

}
