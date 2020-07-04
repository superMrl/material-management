package com.jckj.materialmanagement.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.security.MessageDigest;
import java.util.UUID;

public class ComUtil {


    private static final String numRegex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";//控制不了00.00的这种数,由NumberUtils.isNum(Str)控制

    /**
     * 判断入参是否 有 null
     *
     * @param objects 入参
     * @param <T>     任意类型
     * @return 有一个是null就返回true
     */
    public static <T> boolean isNull(T... objects) {
        for (T obj : objects)
            if (null == obj || "".equals(obj)) {
                return true;
            }
        return false;
    }


    /**
     * 验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        return NumberUtils.isNumber(str) && str.matches(numRegex);
    }


    public static String getRandomUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


}
