package com.jckj.materialmanagement.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class MathUtils {

    private static final DecimalFormat DF_2 = new DecimalFormat("######0.##");
    private static final DecimalFormat DF_4 = new DecimalFormat("######0.####");

    private static final DecimalFormat DF_MONEY = new DecimalFormat("######0.00");

    public static String num2Str(Number num) {
        String result = String.valueOf(num);

        // 如果包含小数点，就保留两位
        if (result.contains(".")) {
            result = DF_2.format(num);
        }

        return result;
    }

    public static String num4Str(Number num) {
        String result = String.valueOf(num);

        // 如果包含小数点，就保留两位
        if (result.contains(".")) {
            result = DF_4.format(num);
        }

        return result;
    }

    public static String moneyToStr(Integer money) {
        return DF_MONEY.format(money / 100.0);
    }

    public static Integer string2Int(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

    public static Long string2Long(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把整数转成浮点数类型.
     *
     * @param value
     * @return
     */
    public static BigDecimal transInt2Bigdecimal100(Integer value) {
        return value == null ? BigDecimal.ZERO : BigDecimal.valueOf(value).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 把浮点型转成整数类型.
     *
     * @param value
     * @return
     */
    public static Integer transBigdecimal2Int100(BigDecimal value) {
        return value == null ? 0 : value.multiply(BigDecimal.valueOf(100)).intValue();
    }

    private static final String[] num_lower = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

    public static String toChinese(int num) {
        if (num > 10) {
            return "";
        }
        return num_lower[num];
    }

}
