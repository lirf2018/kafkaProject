package com.uhuibao.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类；SimpleDateFormat实例属于非线程同步的，必须注意线程问题。
 */
public class DateUtil {

    private final static Logger log = Logger.getLogger(DateUtil.class);

    public static String DEFAULT_DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String DEFAULT_DATE_FORMAT_SHORT = "yyyy-MM-dd";

    public static String MONTH = "yyyy-MM";

    /**
     * @return 根据默认格式(yyyy - MM - dd HH : mm : ss)获取当前时间
     */
    public static String getNow() {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT_LONG)
                .format(new Date());
    }

    /**
     * @param format - 日期格式
     * @return 根据参数日期格式获取当前时间
     */
    public static String getNow(String format) {
        SimpleDateFormat sdf = null;
        if (null == format || "".equals(format))
            sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_LONG);
        else
            sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 获得昨天的日期
     *
     * @return
     */
    public static String yestoday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_SHORT);
        return sdf.format(cal.getTime());
    }


    /**
     * 获得当前月份
     *
     * @return
     */
    public static String currMonth() {
        SimpleDateFormat sdf = sdf = new SimpleDateFormat(MONTH);
        return sdf.format(new Date());
    }

    /**
     * 获得上个月份
     *
     * @return
     */
    public static String lastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = sdf = new SimpleDateFormat(MONTH);
        return sdf.format(cal.getTime());
    }

    /**
     * @param date - 字符型日期
     * @return 根据默认格式(yyyy - MM - dd HH : mm : ss)获取日期
     * @throws ParseException - 如果日期格式与默认格式不匹配不能进行转换
     */
    public static Date convertStrToDate(String date) throws ParseException {
        if (null == date || "".equals(date))
            return null;
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT_LONG).parse(date);
    }

    public static Date convertStrToDate(String date, String format)
            throws ParseException {
        if (null == date || "".equals(date))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    public static Date convertObjectToDate(Object object) throws ParseException {
        if (object == null) {
            return null;
        }
        return convertStrToDate(String.valueOf(object));
    }

    public static Date convertObjectToDate(Object object, String format)
            throws ParseException {
        if (object == null) {
            return null;
        }
        return convertStrToDate(String.valueOf(object), format);
    }

    public static String convertDateToStr(Date date) {
        if (date == null)
            return null;
        else
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT_LONG).format(date);
    }

    public static String convertDateToStr(Date date, String format) {
        SimpleDateFormat sdf = null;
        if (null == format || "".equals(format))
            sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_LONG);
        else
            sdf = new SimpleDateFormat(format);

        return sdf.format(date);
    }

    public static int getSecondOfNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.SECOND);
    }

    public static int getMinuteOfNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MINUTE);
    }

    public static String getTimeByMillSecond(long ms) {
        if (ms >= 1000) {
            long s = ms / 1000;
            return getTimeBySecond(s);
        } else {
            return null;
        }
    }

    public static String getTimeBySecond(long second) {
        String h = String.valueOf(second / 3600);
        if (h.length() < 2)
            h = "0" + h;
        String m = String.valueOf(second % 3600 / 60);
        if (m.length() < 2)
            m = "0" + m;
        String s = String.valueOf(second % 3600 % 60);
        if (s.length() < 2)
            s = "0" + s;
        return h + ":" + m + ":" + s;
    }

    public static String getTimePlus(Date beginDate, Date endDate) {
        if (null == beginDate || null == endDate) {
            return "";
        }
        return getTimeByMillSecond(Math.abs(endDate.getTime()
                - beginDate.getTime()));
    }

    public static Date addSeconds(Date date, int seconds) {
        long ldate = date.getTime();
        ldate = ldate + seconds * 1000;
        return new Date(ldate);
    }

    public static Date addMinutes(Date date, int minutes) {
        long ldate = date.getTime();
        ldate = ldate + (long) minutes * 1000l * 60l;
        return new Date(ldate);
    }

    public static Date addHours(Date date, int hours) {
        long ldate = date.getTime();
        ldate = ldate + (long) hours * 1000l * 60l * 60l;
        return new Date(ldate);
    }

    public static Date addDays(Date date, int days) {
        if (date == null)
            return null;
        long ldate = date.getTime();
        ldate = ldate + (long) days * 1000l * 60l * 60l * 24l;
        return new Date(ldate);
    }

    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static String formatDateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String strDate = "";
        try {
            strDate = sdf.format(date);
        } catch (Exception e) {
            log.info("异常", e);
        }
        return strDate;
    }

    /**
     * 如果d1>d2,返回1; 如果d1<d2,返回-1; 否则返回0
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int compare(Date d1, Date d2) {
        if (d1.after(d2)) {
            return 1;
        } else if (d1.before(d2)) {
            return -1;
        } else {
            return 0;
        }

    }

    /**
     * 日期比较
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期比较
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(String DATE1, String DATE2, String format) {

        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 返回格式为传入时间的前或后一天 String
     *
     * @param format 2015-02-02
     * @param date   2015-02-03
     * @param d      1
     * @return
     */
    public static String getDate_lastOrNext(String format, String date, int d) {
        // String date=formatDateToStr(nowdata, "yyyy-MM-dd");
        if (date.split(" ").length > 1) {
            date = date.split(" ")[0];
        }

        String[] getDate = date.split("-");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(getDate[0]),
                Integer.parseInt(getDate[1]) - 1, Integer.parseInt(getDate[2]));
        // calendar.set(2014, 2, 1);
        calendar.add(Calendar.DAY_OF_MONTH, d);
        // calendar.add(Calendar.MONTH, d);
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(calendar.getTime());
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
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
    public static int daysBetween(String smdate, String bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 日期转换成时间戳
     *
     * @param inVal
     * @return
     */
    public static long fromDateStringToLong(String inVal) { // 此方法计算时间毫秒
        Date date = null; // 定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT_LONG);
        try {
            date = inputFormat.parse(inVal); // 将字符型转换成日期型
        } catch (Exception e) {
            log.info("异常", e);
        }
        return date.getTime(); // 返回毫秒数
    }

    public static void main(String[] args) {
        try {
            String d1 = "2017-05-20";
            String d2 = "2017-05-21";
            System.out.println(daysBetween(d1, d2));
        } catch (ParseException e) {
            log.info("异常", e);
        }
    }
}
