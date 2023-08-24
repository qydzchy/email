package com.ruoyi.email;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DynamicDateLabel {

    public static void main(String[] args) {
        LocalDateTime mailTime1 = LocalDateTime.now().minusHours(3);   // 今天发送的
        LocalDateTime mailTime2 = LocalDateTime.now().minusDays(1);   // 昨天发送的
        LocalDateTime mailTime3 = LocalDateTime.now().minusDays(6);   // 6天前发送的，上周的某天
        LocalDateTime mailTime4 = LocalDateTime.now().minusDays(40);  // 40天前发送的，属于今年的其他月份
        LocalDateTime mailTime5 = LocalDateTime.now().minusDays(1000); // 1年前发送的

        System.out.println(getDynamicLabel(mailTime1)); // 今天
        System.out.println(getDynamicLabel(mailTime2)); // 昨天
        System.out.println(getDynamicLabel(mailTime3)); // 上周日（具体显示根据当天日期计算）
        System.out.println(getDynamicLabel(mailTime4)); // 2023年05月 (具体月份)
        System.out.println(getDynamicLabel(mailTime5)); // 2022年08月 (具体年月)
    }

    public static String getDynamicLabel(LocalDateTime mailDateTime) {
        LocalDateTime now = LocalDateTime.now();

        long daysBetween = ChronoUnit.DAYS.between(mailDateTime, now);
        if (daysBetween == 0) {
            return "今天";
        } else if (daysBetween == 1) {
            return "昨天";
        } else if (daysBetween > 1 && daysBetween <= 7) {
            DayOfWeek dayOfWeek = mailDateTime.getDayOfWeek();
            return "上周" + dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.CHINESE);
        } else if (mailDateTime.getYear() == now.getYear()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            return mailDateTime.format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            return mailDateTime.format(formatter);
        }
    }
}
