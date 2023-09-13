package com.ruoyi.email.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String formatSendTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy hh:mm a", Locale.ENGLISH);
        return sdf.format(date);
    }
}
