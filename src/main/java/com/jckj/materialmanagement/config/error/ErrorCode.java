package com.jckj.materialmanagement.config.error;

/**
 * 错误代码常量类

 */
public class ErrorCode {

    public static final String SUCCESS = "000";
    public static final String FAILED = "999";
    public static final String DATA_NOT_FIND = "998";
    public static final String DATA_SAVE_FAIL = "997";
    public static final String DATA_UPDATE_FAIL = "996";
    public static final String DATA_DEL_FAIL = "995";
    public static final String DATA_NAME_EXIST = "994";
    public static final String NUM_OF_DATA_ERROR = "993";//{0}对应的数值格式错误



    public static final String USER_TELEPHONE_HAS_REGISTER = "001";
    public static final String USER_REGISTER_FAIL = "002";
    public static final String USER_LOGIN_FAIL = "003";
    public static final String USER_LOGIN_SUCCESS = "004";
    public static final String USER_TOKEN_FAIL = "005";

    //入库code码
    public static final String INSTORE_SAVE_SUCCESS = "100";
    public static final String INSTORE_SAVE_FAIL = "101";
    public static final String INSTORE_SELECT_SUCCESS = "102";


    //字典code
    public static final String DICT_TYPE_ERROR= "201";
    public static final String DICT_NAME_EMPTY= "202";
    public static final String DICT_ID_NOT_FIND= "203";//{0}对应的字典数据不存在

    //物资code


    //出库code码
    public static final String OUT_SAVE_SUCCESS = "400";
    public static final String OUT_SAVE_FAIL = "401";

}
