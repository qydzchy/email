package com.ruoyi.customer.service.handler.customer.column.utils;

import java.util.ArrayList;
import java.util.List;

public class ColumnUtils {

    /**
     * object 转 list
     * @param obj   需要转换的List对象
     * @param clazz List中元素的class
     * @param <T>
     * @return
     */
    public static <T> List<T> objectToList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        // 判断 obj 是否包含 List 类型
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                // 使用Class.cast做类型转换
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
