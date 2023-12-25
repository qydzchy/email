package com.ruoyi.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {

    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        // 正则表达式模式
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(emailRegex);

        // 创建匹配器
        Matcher matcher = pattern.matcher(email);

        // 执行匹配并返回结果
        return matcher.matches();
    }
}
