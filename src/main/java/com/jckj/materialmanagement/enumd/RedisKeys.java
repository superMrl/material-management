package com.jckj.materialmanagement.enumd;

/**
 * 缓存字典
 * <p>
 * 命名建议：
 * 1 缓存通用前缀为: PREFIX_
 * 2 缓存 key 前缀为: KEY_
 * 3 缓存失效时间前缀为: EXPIRE_
 */
public class RedisKeys {

    /**
     * 最外层 prefix
     */
    public static final String PREFIX_TQ = "m_";

    /**
     * 字典   登录员工token
     */
    public static final String LOGIN_USER_TOKEN = PREFIX_TQ + "login_%s";


}
