package com.jckj.materialmanagement.utils;

import java.security.MessageDigest;

public class ComUtil {


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

}
