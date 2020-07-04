package com.jckj.materialmanagement.utils;


import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 日期处理工具
 * Created by yangle on 2018/3/23.
 */
public class DateUtils {

    public static String DATE_MONTH = "yyyy.MM";
    public static String DATE_REQ_PATTERNYEAR = "yyyy";
    public static String DATE_REQ_PATTERNs = "yyyyMM";
    public static String DATE_DB_PATTERNS = "yyyy-MM";
    public static String DATE_REQ_PATTERN = "yyyyMMdd";
    public static String DATE_DB_PATTERN = "yyyy-MM-dd";
    public static String TIME_REQ_PATTERN = "yyyyMMddHHmmss";
    public static String TIME_DB_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String TIME_SHOW_PATTERN = "yyyy年MM月dd日 HH:mm:ss";
    public static String TIME_MIN_PATTERN = "yyyy-MM-dd HH:mm";
    public static String TIME_PATTERN = "HH:mm:ss";
    public static String DATE_REQ_PATTERN_MONTH = "MMdd";
    public static String TIME_REQ_PATTERN_MIN = "yyyyMMddHHmm";
    public static String TIME_MOB_PATTERN = "MM月dd日";
    public static String TIME_SHORT_PATTERN = "HH:mm";
    public static String TIME_ddHHmm = "HH:mm";
    public static String DATE_SLOPE_PATTERN = "yyyy/MM/dd";

    public final static String SEPERATE_LINE = "-";
    public final static String SEPERATE_COLON = ":";
    public final static String SEPERATE_DOT = ",";

    /**
     * 获取数据库存储格式的时间,主要用于存储actiontime和createtime例:20161108111111
     *
     * @return
     */
    public static Long getDBTime() {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(TIME_REQ_PATTERN);

        return Long.parseLong(dateFormat.format(date));
    }

    public static Long getDBDate() {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERN);

        return Long.parseLong(dateFormat.format(date));
    }


    public static String getDate(Date date, String format) {

        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date now() {
        return new Date();
    }

    public static String formatDateToDB(String dateStr, String reqPattern, String dbPattern) {

        SimpleDateFormat df = new SimpleDateFormat(reqPattern);

        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            return dateStr;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dbPattern);

        return sdf.format(date);
    }

    /**
     * 格式化数据
     *
     * @param dateStr
     * @return
     */
    public static String formatDateToDB(String dateStr) {

        return formatDateToDB(dateStr, DATE_REQ_PATTERN, DATE_DB_PATTERN);

    }


    public static Long formatDateTime(Date date, String pattern) {
        return Long.parseLong(new SimpleDateFormat(pattern).format(date));
    }


    /**
     * 将string型字符串解析成 Date 型
     *
     * @param dateStr 日期
     * @param format  日期格式
     * @return date型日期
     */
    public static Date formatStr2Date(String dateStr, String format) {

        SimpleDateFormat df = new SimpleDateFormat(format);

        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
        }

        return date;
    }

    public static String formatTimeToClient(Long timestamp, String format) {

        Date dt = new Date(timestamp);
        DateFormat df = new SimpleDateFormat(format);

        return df.format(dt);
    }

    /**
     * 获取当前月份的指定日期
     *
     * @param day
     * @return 格式:yyyyMMdd
     */
    public static Long getThisDate(String day) {
        if (day.length() < 2) {
            return Long.parseLong(getThisMonth().toString() + "0" + day);
        } else {
            return Long.parseLong(getThisMonth().toString() + day);
        }
    }

    /**
     * 获取当前月份
     *
     * @return 格式:yyyyMM
     */
    public static Long getThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        DateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_REQ_PATTERNs);
        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    /**
     * 月份加减
     *
     * @param month 格式:yyyyMM
     * @param num   正数加，负数减
     * @return 格式:yyyyMM
     */
    public static Long dateAddMonth(Long month, int num) {
        String dateStr = month.toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, NumberUtils.toInt(dateStr.substring(0, 4)));
        calendar.set(Calendar.MONTH, NumberUtils.toInt(dateStr.substring(4, 6)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.MONTH, num);

        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERNs);

        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }


    /**
     * 将yyyyMMddHHmmSS 转成 HHmm
     *
     * @param time
     * @return
     */
    public static String parseTimeForWorkLineShow(Long time) {
        return String.format("%04d", (time % 1000000 / 100));

    }

    /**
     * Long型时间分钟数加减
     *
     * @param time
     * @param count
     * @return
     */
    public static Long timeAddMinutes(Long time, int count) {
        Date date = formatStr2Date(time.toString(), TIME_REQ_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, count);

        return formatDateTime(calendar.getTime(), TIME_REQ_PATTERN);

    }

    /**
     * Long型时间秒数加减
     *
     * @param time
     * @param count
     * @return
     */
    public static Long timeAddSecond(Long time, int count) {
        Date date = formatStr2Date(time.toString(), TIME_REQ_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, count);

        return formatDateTime(calendar.getTime(), TIME_REQ_PATTERN);

    }


    public static List<Long> parseWorkMonth(Long startDate, Long endDate) {
        List<Long> result = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();

        while (startDate / 100 <= endDate / 100) {
            result.add(startDate / 100);
            Date day = DateUtils.formatStr2Date(String.valueOf(startDate), DateUtils.DATE_REQ_PATTERN);
            calendar.setTime(day);
            calendar.add(Calendar.MONTH, 1);
            DateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_REQ_PATTERN);
            startDate = Long.parseLong(dateFormat.format(calendar.getTime()));
        }
        return result;
    }

    /**
     * 计算工龄.拿当天日期减去入职日期.
     *
     * @param startDate 入职日期
     * @return
     */
    public static Long calDiffAgeDays(Long startDate) {
        return calDiffDays(startDate, true);
    }

    private static Long calDiffDays(Long date, boolean flag) {
        Calendar calendar = Calendar.getInstance();
        Long carrentTime = calendar.getTimeInMillis();

        calendar.setTime(formatStr2Date(date.toString(), DATE_REQ_PATTERN));
        Long calTime = calendar.getTimeInMillis();
        long subSum = 0;
        if (flag) {
            //当前时间减去过去时间
            subSum = carrentTime - calTime;
        } else {
            //终止时间减去当天时间
            subSum = calTime - carrentTime;
        }
        return (subSum / (3600 * 24 * 1000));
    }

    /**
     * 计算两个时间段相差的天数.
     *
     * @param endDate 合同终止日期
     * @return
     */
    public static Long calDiffDays(Long endDate) {
        return calDiffDays(endDate, false);
    }

    public static Long parseMinTimeForDate(Long longDate) {
        String dateStr = longDate.toString() + "000000";
        return Long.parseLong(dateStr);
    }

    public static Long parseMaxTimeForDate(Long longDate) {
        String dateStr = longDate.toString() + "235959";
        return Long.parseLong(dateStr);
    }

    /**
     * 把时间段信息转换成long型日期格式.
     *
     * @param currentDate 当前日期
     * @param periodTime
     * @return
     */
    public static List<Long> parsePeriodTime(Long currentDate, String periodTime) {
        //13:09-15:00
        List<Long> result = Lists.newArrayList();
        String[] arr = periodTime.split(SEPERATE_LINE);
        String[] oneArr = arr[0].split(SEPERATE_COLON);
        String[] twoArr = arr[1].split(SEPERATE_COLON);

        String dateStr = currentDate.toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, MathUtils.string2Int(dateStr.substring(0, 4)));
        calendar.set(Calendar.MONTH, MathUtils.string2Int(dateStr.substring(4, 6)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, MathUtils.string2Int(dateStr.substring(6, 8)));

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(oneArr[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(oneArr[1]));
        calendar.set(Calendar.SECOND, 0);
        result.add(DateUtils.formatDateTime(calendar.getTime(), DateUtils.TIME_REQ_PATTERN));

        // 如果 periodTime 后面的时段比前面的时段小，日期增加一天 -- yangle
        if (comparePeriodTimeArr(oneArr, twoArr)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(twoArr[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(twoArr[1]));
        calendar.set(Calendar.SECOND, 0);
        result.add(DateUtils.formatDateTime(calendar.getTime(), DateUtils.TIME_REQ_PATTERN));

        return result;
    }

    /**
     * 从long型日期中摘取小时分钟时间.
     *
     * @param datetime
     * @return
     */
    public static String parsePeriodTime(Long datetime) {
        String str = datetime.toString();
        StringBuilder builder = new StringBuilder();
        builder.append(str.substring(8, 10));
        builder.append(SEPERATE_COLON);
        builder.append(str.substring(10, 12));
        return builder.toString();
    }

    /**
     * 把日数字转成long型的日期返回.
     *
     * @param day
     * @param format
     * @return
     */
    public static Long formatDayToLongDate(int day, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return formatDateTime(calendar.getTime(), format);
    }


    public static String parseDateToShow(Long date) {
        Date d = parseDateFromLong(date);
        DateFormat dateFormat = new SimpleDateFormat(DATE_DB_PATTERN);
        return dateFormat.format(d);
    }

    /**
     * 将yyyyMMddHHmmSS 转成  HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String parseTimeToHHmm(Long dateTime) {
        return DateUtils.formatDateToDB(dateTime.toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_SHORT_PATTERN);
    }

    /**
     * 将yyyyMMddHHmmSS 转成  5日 03:02:01
     *
     * @param dateTime
     * @return
     */
    public static String parseTimeToddHHmm(Long dateTime) {
        return DateUtils.formatDateToDB(dateTime.toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_ddHHmm);
    }

    /**
     * 将yyyyMMddHHmmSS 转成  HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String parseHourAndMinsToShow(Long dateTime) {
        return DateUtils.formatDateToDB(dateTime.toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_DB_PATTERN).substring(11);
    }

    /**
     * 将yyyyMMddHHmmSS 转成  5日 03:02:01
     *
     * @param dateTime
     * @return
     */
    public static String parseDateHourMinsToShow(Long dateTime) {
        return DateUtils.formatDateToDB(dateTime.toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_SHOW_PATTERN).substring(8);
    }

    /**
     * 将yyyyMMdd转换为yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String parseDate2YMD(String date) {
        return DateUtils.formatDateToDB(date, DateUtils.DATE_REQ_PATTERN, DateUtils.TIME_SHOW_PATTERN).substring(0, 11);
    }

    public static String formatTimeToDB(String dateStr) {
        return formatDateToDB(dateStr, TIME_REQ_PATTERN, TIME_DB_PATTERN);
    }

    /**
     * 将yyyyMMddHHmmSS 转成 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String parseDateTimeToShow(Long dateTime) {
        return DateUtils.formatDateToDB(dateTime.toString(), DateUtils.TIME_REQ_PATTERN, DateUtils.TIME_DB_PATTERN);
    }

    /**
     * 计算两个时间段相差的天数.
     *
     * @return
     */
    public static Long calDiffDays(Long startDate, Long endDate) {
        if (startDate.compareTo(endDate) > 0) {
            return 0l;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatStr2Date(startDate.toString(), DATE_REQ_PATTERN));
        Long startTime = calendar.getTimeInMillis();

        calendar.setTime(formatStr2Date(endDate.toString(), DATE_REQ_PATTERN));
        Long endTime = calendar.getTimeInMillis();

        return (endTime - startTime) / (3600 * 24 * 1000);
    }

    public static String formatDateTimes(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Integer getWeekIndex(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDateFromLong(date));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getWeekDayForShow(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDateFromLong(date));
        return parseWeekDayForShow(calendar.get(Calendar.DAY_OF_WEEK));

    }

    public static String parseWeekDayForShow(int weekday) {
        switch (weekday) {
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
            default:
                return " ";
        }
    }

    public static Date parseDateFromLong(Long date) {
        String dateStr = date.toString();
        if (ComUtil.isNull(dateStr) || dateStr.length() != 8) {
            return null;
//            throw new BusinessException("-1", "日期格式不对，支持格式yyyyMMdd,传入参数为；" + dateStr);
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, MathUtils.string2Int(dateStr.substring(0, 4)));
        cal.set(Calendar.MONTH, MathUtils.string2Int(dateStr.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, MathUtils.string2Int(dateStr.substring(6, 8)));
        return cal.getTime();
    }

    private static boolean comparePeriodTimeArr(String[] oneArr, String[] twoArr) {
        // 比较小时数
        if (Integer.parseInt(oneArr[0]) < Integer.parseInt(twoArr[0])) {
            return false;
        }
        // 比较分钟数
        if ((Integer.parseInt(oneArr[0]) == Integer.parseInt(twoArr[0]))
                && (Integer.parseInt(oneArr[1]) <= Integer.parseInt(twoArr[1]))) {
            return false;
        }

        return true;
    }

    /**
     * 设置年月信息.
     *
     * @param calendar
     * @param queryDate
     * @return
     */
    public static Calendar editYearMonth(Calendar calendar, Long queryDate) {
        int year = MathUtils.string2Int(queryDate.toString().substring(0, 4));
        int month = MathUtils.string2Int(queryDate.toString().substring(4, 6));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar;
    }

    public static Long getThisYearFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_REQ_PATTERNs);
        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    public static Long getLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //上个月
        calendar.add(Calendar.MONTH, -1);
        return DateUtils.formatDateTime(calendar.getTime(), DateUtils.DATE_REQ_PATTERNs);
    }

    public static Long addYear(Long date, int num) {
        String dateStr = date.toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(4, 6)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6, 8)));

        //上个月
        calendar.add(Calendar.YEAR, num);
        return formatDateTime(calendar.getTime(), DateUtils.DATE_REQ_PATTERN);
    }


    public static Long parseCurrentPeriod(Long date) {
        Long today = getDBDate();
        while (true) {
            if (date <= today && today < addYear(date, 1)) {
                return date;
            } else if (today < date) {
                date = addYear(date, -1);//上一个周期
            } else {
                date = addYear(date, 1);// 下一个周期
            }
        }
    }

    /**
     * 获取该月份最大的日期
     *
     * @param month
     * @return
     */
    public static Long getMaxDateInMonth(Long month) {
        Calendar calendar = Calendar.getInstance();
        String dateStr = month.toString();
        calendar.set(Calendar.YEAR, MathUtils.string2Int(dateStr.substring(0, 4)));
        calendar.set(Calendar.MONTH, MathUtils.string2Int(dateStr.substring(4, 6)));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERN);
        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    /**
     * 获取该月份最小的日期
     *
     * @param month
     * @return
     */
    public static Long getMinDateInMonth(Long month) {
        Calendar calendar = Calendar.getInstance();
        String dateStr = month.toString();
        calendar.set(Calendar.YEAR, MathUtils.string2Int(dateStr.substring(0, 4)));
        calendar.set(Calendar.MONTH, MathUtils.string2Int(dateStr.substring(4, 6)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERN);
        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    public static Integer getAge(Long entryDate, Long lineDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_REQ_PATTERN);
        try {
            Date a = format.parse(entryDate.toString());
            Date b = format.parse(lineDate.toString());
            Long c = (b.getTime() - a.getTime()) / (1000 * 3600 * 24);
            return (c / 365) < 0 ? 0 : (int) (c / 365);
        } catch (ParseException e) {
            return 0;
        }
    }

    public static List<Long> paresHoildayPeriod(Long startDate, Long endDate, Long lineDate) {
        List<Long> lines = Lists.newArrayList();

        while (true) {
            if (startDate < lineDate) {
                lineDate = addYear(lineDate, -1);
            } else {
                break;
            }
        }

        while (true) {
            lines.add(lineDate);
            if (lineDate > endDate) {
                break;
            } else {
                lineDate = addYear(lineDate, 1);
            }
        }

        return lines;
    }


    /**
     * 获取当前年的数字.
     *
     * @return
     */
    public static int parseCurrentYearNum() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Long型日期加减天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Long dateAddDays(Long date, int days) {

        String dateStr = date.toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, MathUtils.string2Int(dateStr.substring(0, 4)));
        calendar.set(Calendar.MONTH, MathUtils.string2Int(dateStr.substring(4, 6)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, MathUtils.string2Int(dateStr.substring(6, 8)));

        calendar.add(Calendar.DAY_OF_MONTH, days);

        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERN);

        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    /**
     * 计算两个日期时间相差的分钟数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer calDiffMinutes(Long startTime, Long endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatStr2Date(startTime.toString(), TIME_REQ_PATTERN));
        calendar.set(Calendar.SECOND, 0);
        Long min = calendar.getTimeInMillis();

        calendar.setTime(formatStr2Date(endTime.toString(), TIME_REQ_PATTERN));
        calendar.set(Calendar.SECOND, 0);
        Long max = calendar.getTimeInMillis();

        return (int) ((max - min) / (1000 * 60));
    }

    /**
     * 计算两个日期时间相差的秒数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer calDiffSeconds(Long startTime, Long endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatStr2Date(startTime.toString(), TIME_REQ_PATTERN));
        Long min = calendar.getTimeInMillis();

        calendar.setTime(formatStr2Date(endTime.toString(), TIME_REQ_PATTERN));
        Long max = calendar.getTimeInMillis();

        return (int) ((max - min) / (1000));
    }

    public static Long getFirstDayByMonth(Long month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (month.intValue() / 100));
        calendar.set(Calendar.MONTH, (month.intValue() % 100) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERN);
        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    public static Long getLastDayByMonth(Long month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (month.intValue() / 100));
        calendar.set(Calendar.MONTH, (month.intValue() % 100));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        DateFormat dateFormat = new SimpleDateFormat(DATE_REQ_PATTERN);
        return Long.parseLong(dateFormat.format(calendar.getTime()));
    }

    public static List<Long> parseWorkDate(Long month, String period) {
        if (!ComUtil.isNull(period)) {
            String start = String.format("%d%02d", month, Integer.valueOf(period.split("-")[0]));
            String end = String.format("%d%02d", month, Integer.valueOf(period.split("-")[1]));
            return DateUtils.parseWorkDate(Long.valueOf(start), Long.valueOf(end));
        } else {
            return DateUtils.parseWorkDate(getMinDateInMonth(month), getMaxDateInMonth(month));
        }
    }

    public static List<Long> parseWorkDate(Long startDate, Long endDate) {
        List<Long> result = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        while (startDate <= endDate) {
            result.add(startDate);
            Date day = DateUtils.formatStr2Date(String.valueOf(startDate), DateUtils.DATE_REQ_PATTERN);
            calendar.setTime(day);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            DateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_REQ_PATTERN);
            startDate = Long.parseLong(dateFormat.format(calendar.getTime()));
        }
        return result;
    }

    /**
     * 检查考勤审核截止日或者工资发放日.
     *
     * @param queryMonth    月份
     * @param auditStopDate 考勤审核截止日或者工资发放日等
     * @return
     */
    public static boolean checkValidateDate(Long queryMonth, Long auditStopDate) {
        if (queryMonth == null) {
            //默认查当月
            return true;
        }

        Calendar calendar = Calendar.getInstance();
        Long currentDay = DateUtils.formatDateTime(calendar.getTime(), DateUtils.DATE_REQ_PATTERN);//当前日期
        Long currentMonth = DateUtils.formatDateTime(calendar.getTime(), DateUtils.DATE_REQ_PATTERNs);//当前月
        if (currentMonth.compareTo(queryMonth) <= 0) {
            //当前月可编辑
            return true;
        }

        calendar.add(Calendar.MONTH, -1);
        Long lastMonth = DateUtils.formatDateTime(calendar.getTime(), DateUtils.DATE_REQ_PATTERNs);//上个月
        if (queryMonth < lastMonth) {
            return false;
        }

        if (auditStopDate != null && currentDay >= auditStopDate) {
            //当前日期大于等于发放日不可以审核，否则可审核
            return false;
        }

        return true;
    }

    public static Integer calEmployeeAge(Long birthday) {
        Long today = getDBDate();
        return (int) (today / 10000 - birthday / 10000);
    }

    /**
     * 当前日期累加月份值.
     *
     * @param currentDate
     * @param month
     * @return
     */
    public static Long calAddMonth(Long currentDate, int month) {
        if (month <= 0) {
            return currentDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.formatStr2Date(currentDate.toString(), DateUtils.DATE_REQ_PATTERN));
        calendar.add(Calendar.MONTH, month);
        return DateUtils.formatDateTime(calendar.getTime(), DateUtils.DATE_REQ_PATTERN);
    }

    /**
     * 当前日期累加减月份值.
     * 格式进行判断输出
     *
     * @param date yyyyMMdd / yyyyMM / yyyy
     * @param as
     * @return
     */
    public static Long calAddOrSubMonth(Long date, int as) {
        Calendar calendar = Calendar.getInstance();
        String d = date.toString();
        String format = null;
        if (d.length() == 4) {
            calendar.setTime(DateUtils.formatStr2Date(d, DateUtils.DATE_REQ_PATTERNYEAR));
            calendar.add(Calendar.YEAR, as);
            format = DateUtils.DATE_REQ_PATTERNYEAR;
        }
        if (d.length() == 6) {
            calendar.setTime(DateUtils.formatStr2Date(d, DateUtils.DATE_REQ_PATTERNs));
            calendar.add(Calendar.MONTH, as);
            format = DateUtils.DATE_REQ_PATTERNs;
        }
        if (d.length() == 8) {
            calendar.setTime(DateUtils.formatStr2Date(d, DateUtils.DATE_REQ_PATTERN));
            calendar.add(Calendar.DATE, as);
            format = DateUtils.DATE_REQ_PATTERN;
        }
        return DateUtils.formatDateTime(calendar.getTime(), format);
    }


    /**
     * 取出指定月份的最后一天日期.
     *
     * @param date
     * @return
     */
    public static Long getMonthLastDate(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatStr2Date(date.toString(), DATE_REQ_PATTERN));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDateTime(calendar.getTime(), DATE_REQ_PATTERN);
    }

    /**
     * 解析周几.
     *
     * @return
     */
    public static String getWeekText(int weekDay) {
        switch (weekDay) {
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            case Calendar.SUNDAY:
                return "周日";
        }
        return null;
    }

    /**
     * 根据年份计算同比和环比日期
     *
     * @param year
     * @return 环比日期 - 201808-201712 同比日期 - 201708 -201612
     */
    public static List<Long> getDateForCompare(Long year) {
        List<Long> result = new ArrayList<>();
        if (ComUtil.isNull(year)) {
            return result;
        }
        //获取当前月
        Calendar calendar = Calendar.getInstance();
        Integer needYear = Integer.valueOf(String.valueOf(year));
        calendar.set(calendar.YEAR, needYear);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        List<String> months = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        years.add(calendar.get(calendar.YEAR));
        years.add(calendar.get(calendar.YEAR) - 1);
        years.add(calendar.get(calendar.YEAR) - 2);
        for (int i = currentMonth; i >= 1; i--) {
            String m = String.valueOf(i);
            if (i < 10) {
                m = "0" + m;
            }
            months.add(m);
        }
        years.forEach(y -> {
            if (String.valueOf(y).equals(String.valueOf(year)) || String.valueOf(y).equals(String.valueOf(year - 1))) {
                months.forEach(m -> {
                    result.add(Long.parseLong(y + m));
                });
            }
            if (String.valueOf(y).equals(String.valueOf(year - 1)) || String.valueOf(y).equals(String.valueOf(year - 2))) {
                result.add(Long.parseLong(y + "12"));
            }
        });

        List<Long> re = result.stream().sorted(Comparator.reverseOrder()).distinct().collect(Collectors.toList());
        return re;
    }

    /**
     * 获取当前日(yyyyMMdd格式)
     *
     * @return
     */
    public static Long getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return formatDateTime(calendar.getTime(), DATE_REQ_PATTERN);
    }

    /**
     * 获取当前日之前N天(yyyyMMdd格式)
     *
     * @return
     */
    public static Long getDayBeforeCurrentDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return formatDateTime(calendar.getTime(), DATE_REQ_PATTERN);
    }

    /**
     * 获取当前日之前N天(yyyyMMdd格式)
     *
     * @return
     */
    public static Long getLastDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDateTime(calendar.getTime(), DATE_REQ_PATTERN);
    }

    /**
     * 日期格式转换为UNIX时间戳
     *
     * @param timestamp
     * @return
     * @throws ParseException
     */
    public static String getUnixtime(String timestamp, String format) {
        String str = null;

        try {
            SimpleDateFormat date_time = new SimpleDateFormat(format);
            Date date = date_time.parse(timestamp);
            long ts = date.getTime();
            str = String.valueOf(ts / 1000);

        } catch (Exception e) {

        }

        return str;
    }

    /**
     * 获取指定时间最近的12个月的年月
     *
     * @return
     */
    public static List<Long> getLatest12Month(Long month) {
        List<Long> yearAndMonths = Lists.newArrayList();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, (month.intValue() / 100));
        cal.set(Calendar.MONTH, (month.intValue() % 100));

        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
            yearAndMonths.add(Long.valueOf(cal.get(Calendar.YEAR) + formatDay(cal.get(Calendar.MONTH) + 1)));
        }
        return yearAndMonths;
    }

    public static String formatDay(int i) {
        String month = "";
        if (i < 10) {
            month = "0" + i;
        } else {
            month = String.valueOf(i);
        }
        return month;
    }

    public static String packageMonthDay(int month, int day) {
        StringBuilder builder = new StringBuilder();
        if (String.valueOf(month).length() == 1) {
            builder.append("0");
        }
        builder.append(month);
        if (String.valueOf(day).length() == 1) {
            builder.append("0");
        }
        builder.append(day);
        return builder.toString();
    }

    public static String[] parseMonthDay(String value) {
        if (value.length() != 4) {
            return null;
        }
        String[] result = new String[2];
        result[0] = value.substring(0, 2);
        result[1] = value.substring(2, 4);
        return result;
    }

    /**
     * 获取当前日期的下个月1日
     *
     * @param value
     * @return
     */
    public static Long getNextMonthFirstDate(Long value) {
        if (value == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.formatStr2Date(value.toString(), DATE_REQ_PATTERN));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtils.formatDateTime(calendar.getTime(), DATE_REQ_PATTERN);
    }

    /**
     * 获取指定月 法定假期天数
     *
     * @param month    201907
     * @param holidays redis中一年的假期集合,逗号分隔,从redis中获取的值
     * @return
     */
    public static Integer getLegalHolidaysMonth(Long month, String holidays) {
        Integer num = 0;
        if (Strings.isNullOrEmpty(holidays)) {
            return num;
        }
        Long min = getMinDateInMonth(month);
        Long max = getMaxDateInMonth(month);
        List<Long> temps = Arrays.stream(holidays.split(",")).map(Long::valueOf).collect(Collectors.toList());
        for (Long t : temps) {
            if (t.compareTo(min) >= 0 && t.compareTo(max) <= 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * 获取一年的所有月份
     *
     * @param year 2019
     * @return 201901~201912
     */
    public static List<Long> getMonthsOfYear(Long year) {
        List<Long> months = Lists.newArrayList();
        Long minMonth = Long.valueOf(String.valueOf(year) + "01");
        months.add(minMonth);
        for (long i = 1; i <= 11; i++) {
            months.add(minMonth + i);
        }
        return months;
    }

    /**
     * 获取一段时间内闰年的个数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int statsLeapYearCount(int startDate, int endDate) {
        if (startDate >= endDate) {
            return 0;
        }
        int startYear = startDate / 10000;
        int endYear = endDate / 10000;
        if (MathUtils.string2Int(String.valueOf(startDate).substring(4, 6)) > 2) {
            //起始日期不包含2月则处理起始日期
            startYear = startYear + 1;
        }
        if (MathUtils.string2Int(String.valueOf(endDate).substring(4, 6)) <= 2) {
            //结束日期不包含2月则处理结束日期
            endYear = endYear - 1;
        }
        if (startYear >= endYear) {
            return 0;
        }

        int count = 0;
        for (int year = startYear; year <= endYear; year++) {
            if (validateLeapYear(year)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 判定是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean validateLeapYear(int year) {
        if (year < 0 || year > 3000) {
            return false;
        }

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }

        return false;
    }

    /**
     * 获取一个月内日期
     * exp:20190101~20190131
     *
     * @param month
     * @return
     */
    public static List<String> getDaysInMonth(Long month) {
        List<String> days = Lists.newArrayList();
        Long min = DateUtils.getMinDateInMonth(month);
        Long max = DateUtils.getMaxDateInMonth(month);

        for (int i = 0; i <= 35; i++) {
            days.add(String.valueOf(min + i));
            if (min + i == max) {
                break;
            }
        }
        return days;
    }
}
