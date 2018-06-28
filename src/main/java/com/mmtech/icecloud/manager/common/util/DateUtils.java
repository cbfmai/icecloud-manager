package com.mmtech.icecloud.manager.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期类型
 */
public class DateUtils {

    /**
     * 得到本日的前后几个月时间
     */
    public static Date getDateBeforeMonth(int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getNow());
        cal.add(Calendar.MONTH, number);
        return cal.getTime();
    }

    /**
     * 获取指定日期前后几个月时间
     *
     * @param date
     * @param number
     * @return
     */
    public static String getDateBeforeMonth(Date date, int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, number);
        return getDateFormat(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 字符转化时间
     *
     * @param str
     * @return
     */
    public static Date stringToDay(String str) {
        String format = "yyyy-MM-dd";
        return StringToDate(str, format);
    }

    /**
     * 字符转化时间
     *
     * @param str
     * @return
     */
    public static Date StringToTime(String str) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return StringToDate(str, format);
    }

    /**
     * 字符串转化时间
     *
     * @param str
     * @return
     */
    private static Date StringToDate(String str, String format) {
        SimpleDateFormat sbf = new SimpleDateFormat(format);
        try {
            return sbf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取有格式的当前时间
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 时间转化
     *
     * @param data
     * @param format
     * @return
     */
    public static String getDateFormat(Date data, String format) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(data);
    }

    /**
     * 时间比较
     *
     * @param max
     * @param min
     * @return
     */
    public static boolean isBefore(String max, String min) {
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date maxDate = df.parse(max);
            Date minDate = df.parse(min);
            return maxDate.before(minDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 时间比较
     *
     * @param max
     * @param min
     * @return
     */
    public static boolean isAfter(String max, String min) {
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date maxDate = df.parse(max);
            Date minDate = df.parse(min);
            return maxDate.after(minDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取当前时间前后 如-5 +5
     *
     * @param format
     * @param days
     * @return
     */
    public static String getTime(String format, int days) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        now.add(Calendar.DATE, days);
        return formatter.format(now.getTime());
    }

    public static String getYearTime(String format, int year) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        now.add(Calendar.YEAR, year);
        return formatter.format(now.getTime());
    }

    /**
     * 获取当前时间前后 如-5 +5
     *
     * @param format
     * @param days
     * @return
     */
    public static Date getTime2(String format, int days) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    /**
     * 指定时间加n秒，可为负数
     *
     * @return
     */
    public static Date dateAddSec(Date date, int sec) {
        if (date == null) {
            return null;
        }
        long time = sec * 1000;
        long currentTime = date.getTime() + time;
        return new Date(currentTime);
    }

    /**
     * 获取指定时间前后 如-5 +5
     *
     * @param time
     * @param format
     * @param days
     * @return
     */
    public static String getTime(String time, String format, int days) {
        Calendar now = Calendar.getInstance();
        DateFormat df = DateFormat.getDateInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            now.setTime(df.parse(time));
            now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter.format(now.getTime());
    }

    /**
     * 获取指定时间前后 如-5 +5
     *
     * @param time
     * @param format
     * @param days
     * @return
     */
    public static Date getTime(Date date, int days) {
        Calendar now = Calendar.getInstance();
        try {
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now.getTime();
    }

    /**
     * 获得当天时间的0点
     *
     * @return
     * @author Kee.Li
     */
    public static Date getCurrentDayZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得当天时间的0点
     *
     * @return
     * @author Kee.Li
     */
    public static Date getDateZeroTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 将date转为yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToTime(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return getDateFormat(date, format);
    }

    /**
     * 将date转为yyyyMMddHHmmssSSS格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToTimeMMss(Date date) {
        String format = "yyyyMMddHHmmssSSS";
        return getDateFormat(date, format);
    }

    /**
     * 将date转为yyyyMMddHHmmss格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToTimeMM(Date date) {
        String format = "yyyyMMddHHmmss";
        return getDateFormat(date, format);
    }

    /**
     * 将date转为yyyy-MM-dd格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToDay(Date date) {
        if (date == null) {
            return null;
        }
        String format = "yyyy-MM-dd";
        return getDateFormat(date, format);
    }

    public static String dateToShortString(Date date) {
        if (date == null) {
            return null;
        }
        String format = "yyyy-MM";
        return getDateFormat(date, format);
    }

    /**
     * 将date转为yyyy-MM-dd HH:mm格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToMinute(Date date) {
        if (date == null) {
            return "";
        }
        String format = "yyyy-MM-dd HH:mm";
        return getDateFormat(date, format);
    }

    public static String dateToPlusDay(Date date) {

        Date nextDay = new Date(date.getTime() + 24 * 60 * 60 * 1000);

        return dateToDay(nextDay);
    }

    /**
     * 计算年龄
     *
     * @param time
     */
    public static int getYear(String time) {
        Date cals = null;
        try {
            cals = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        Calendar cal = Calendar.getInstance();

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1; // 注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(cals);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    // do nothing
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }

        return age <= 0 ? 0 : age;
    }

    /**
     * 得到明天的日期
     *
     * @return
     */
    public static Date getNextDat() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 时间加n分钟
     *
     * @return
     */
    public static Date dateAddMin(Date date, int min) {
        if (date == null) {
            return null;
        }
        long time = min * 60 * 1000;
        long currentTime = date.getTime() + time;
        return new Date(currentTime);
    }

    public static Date dateAddDay(Date date, int min) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, min);
        return cal.getTime();
    }

    /**
     * 时间加n月份
     *
     * @return
     */
    public static Date dateAddMonth(Date date, int mon) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    public static Date dateAddYear(Date date, int year) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 获取指定时间是星期几。 星期天为0。星期一为:1
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取指定时间是星期几。 星期天为0。星期一为:1
     *
     * @param date
     * @return
     */
    public static int getWeek(String str) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(stringToDay(str));
        return ca.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 根据指定日期判断属于星期几。
     *
     * @return
     */
    public static String getWeekToString(String str) {
        int week = getWeek(str);
        switch (week) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
        }
        return "";
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        Integer year = a.get(Calendar.YEAR);
        Integer month = a.get(Calendar.MONTH) + 1;
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate, String format)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 时间戳转时间，微信上传多媒体后返回的时间戳，在调用此方法时需*1000
     *
     * @param stamp 时间戳
     * @return
     */
    public static Date stampToDate(String stamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = new Long(stamp);
        String d = format.format(time);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间的年份
     *
     * @param stamp 时间戳
     * @return
     */
    public static Integer getYear() {
        Calendar now = Calendar.getInstance();
        Integer year = now.get(Calendar.YEAR);
        return year;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param one 时间参数 1：
     * @param two 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2)
            throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                // diff = time1 - time2;
                return "00天00小时00分00秒";
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(!(day >= 10) ? "0" + day + "天" : day + "天");
        sb.append(!(hour >= 10) ? "0" + hour + "小时" : hour + "小时");
        sb.append(!(min >= 10) ? "0" + min + "分" : min + "分");
        sb.append(!(sec >= 10) ? "0" + sec + "秒" : sec + "秒");
        return sb.toString();
        // return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }


    /**
     * 获取两个日期相差的毫秒值
     *
     * @param smdate
     * @param bdate
     * @return
     */
    public static long timesBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        return (time2 - time1);
    }

    /**
     * 加指定多个月，当天零点
     *
     * @param date  日期
     * @param month 加多少个月
     * @return
     */
    public static Date toFutureMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String dateFormatMonthAndDay(Date date) {
        return getDateFormat(date, "MM") + "月" + getDateFormat(date, "dd") + "日";

    }

    /**
     * 获取两个日期相差的月数(精确到日的判断)
     *
     * @param d1 较大的日期
     * @param d2 较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }
}
