package com.demo.spring_boot.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

//import gxlu.sysmanage.constant.Consts;

/**
 * 日期转换类
 */
public class DateUtils {

    public final static DateFormat FORMAT_YYYY_MM_DD = new SimpleDateFormat(
            "yyyy-MM-dd");

    public final static DateFormat FORMAT_DD_MM_YYYY = new SimpleDateFormat(
            "dd/MM/yyyy");

    public final static DateFormat FORMAT_YYYYMMDD = new SimpleDateFormat(
            "yyyyMMdd");

    public final static DateFormat FORMAT_YYYYMM = new SimpleDateFormat(
            "yyyyMM");

    public final static DateFormat FORMAT_MM = new SimpleDateFormat("MM");
    public final static DateFormat FORMAT_DD = new SimpleDateFormat("dd");

    public final static DateFormat FORMAT_YYYY = new SimpleDateFormat("yyyy");
    public final static DateFormat FORMAT_YYYYMMDDHHMMSS = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public final static DateFormat FORMAT_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public final static DateFormat FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS");
    public final static DateFormat FORMAT_YYYY_MM_DD_HH_MM = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    public final static DateFormat FORMAT_YYMMDD_HH_MM = new SimpleDateFormat(
            "yyMMdd HH:mm");

    /**
     * 获得当前年月,格式为YYYY-MM
     *
     * @return
     */
    public static String getCurrentMonthYYYYMM() {
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth < 10) {
            return currentYear + "-0" + currentMonth;
        } else {
            return currentYear + "-" + currentMonth;
        }
    }

    public static String getDateByFormat(Date date, DateFormat dateFormat) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

    /**
     * 获得当前年月,格式为YYYYMM
     *
     * @return
     */
    public static String getCurrentMonthYYYYMM2() {
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth < 10) {
            return currentYear + "0" + currentMonth;
        } else {
            return currentYear + "" + currentMonth;
        }
    }

    /**
     * 获得上一个月,格式为YYYY-MM
     *
     * @return
     */
    public static String getPreviousMonthYYYYMM() {
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth - 1 < 1) {
            currentYear--;
            currentMonth = 12 + currentMonth - 1;
            return currentYear + "" + currentMonth;
        } else {
            currentMonth--;
            if (currentMonth < 10) {
                return currentYear + "-0" + currentMonth;
            } else {
                return currentYear + "-" + currentMonth;
            }

        }
    }

    /**
     * 获得当前年,格式为MM
     *
     * @return
     */
    public static String getCurrentYear() {
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        return year + "";
    }

    /**
     * 获得当前月,格式为MM
     *
     * @return
     */
    public static String getPreviousMonthMM() {
        Calendar calendar = new GregorianCalendar();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth - 1 < 1) {
            currentMonth = 12 + currentMonth - 1;
            return currentMonth + "";
        } else {
            currentMonth--;
            return currentMonth + "";
        }
    }

    /**
     * 获得当前月,格式为MM
     *
     * @return
     */
    public static String getCurrentMonthMM() {
        Calendar calendar = new GregorianCalendar();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth < 10) {
            return "0" + currentMonth;
        } else {
            return currentMonth + "";
        }
    }

    /**
     * 获得当前周
     *
     * @return
     */
    public static int getCurrentWeek() {
        Calendar calendar = new GregorianCalendar();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return currentWeek;
    }

    /**
     * 获取周
     *
     * @return
     * @throws ParseException
     */
    public static String getWeek(String today) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return currentWeek + "";
    }


    /**
     * 获得上一个月,格式为YYYYMM
     *
     * @return
     */
    public static String getPreviousMonthYYYYMM2() {
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth - 1 < 1) {
            currentYear--;
            currentMonth = 12 + currentMonth - 1;
            return currentYear + "" + currentMonth;
        } else {
            currentMonth--;
            if (currentMonth < 10) {
                return currentYear + "0" + currentMonth;
            } else {
                return currentYear + "" + currentMonth;
            }

        }
    }

    /**
     * 获取当前季度
     *
     * @return
     */
    public static Integer getQuarter() {
        Calendar calendar = new GregorianCalendar();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int quarter = 1;
        if (currentMonth == 1 || currentMonth == 2 || currentMonth == 3) {
            quarter = 1;
        } else if (currentMonth == 4 || currentMonth == 5 || currentMonth == 6) {
            quarter = 2;
        } else if (currentMonth == 7 || currentMonth == 8 || currentMonth == 9) {
            quarter = 3;
        } else if (currentMonth == 10 || currentMonth == 11 || currentMonth == 12) {
            quarter = 4;
        }
        return quarter;
    }

    /**
     * 获得上一年的最后一个月,格式为YYYYMM
     *
     * @return
     */
    public static String getPreviousYearYYYYMM() {
        DateFormat df = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -1);
        return df.format(cal.getTime()) + "12";
    }

    /**
     * 得到当前日期后的N天的日期
     *
     * @param days 天数
     * @return
     */
    public static Date getBackDaysOfNow(int days) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(now.getTimeInMillis() + 1000L * 3600 * 24 * days);
        return now.getTime();
    }

    /**
     * 得到当前日历的日期字符串
     *
     * @param calendar 日历对象
     * @return
     */
    public static String getCalendarStr(Calendar calendar) {
        StringBuffer calendarStr = new StringBuffer();
        calendarStr.append(calendar.get(Calendar.YEAR)).append("-");
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        calendarStr.append(currentMonth).append("-");
        calendarStr.append(calendar.get(Calendar.DAY_OF_MONTH));
        return calendarStr.toString();
    }

    /**
     * 得到当前日期前的N天的日期
     *
     * @param days 天数
     * @return
     */
    public static Date getPreviousDaysOfNow(int days) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(now.getTimeInMillis() - 1000L * 3600 * 24 * days);
        return now.getTime();
    }

    /**
     * 得到当前时间,格式为yyyy-MM-dd
     *
     * @return
     */
    public synchronized static String getCurrentDate() {
        Date now = new Date();
        return FORMAT_YYYY_MM_DD.format(now);
    }

    /**
     * 得到当前时间,格式为yyyy-MM-dd
     *
     * @return
     */
    public synchronized static String getCurrentDateddMMyyyy() {
        Date now = new Date();
        return FORMAT_DD_MM_YYYY.format(now);
    }

    /**
     * 得到当前时间,格式为yyyyMMdd
     *
     * @return
     */
    public synchronized static String getCurrentDateyyyyMMdd() {
        Date now = new Date();
        return FORMAT_YYYYMMDD.format(now);
    }

    /**
     * 得到本月的第一天(如：得到是2007-04-01 今天是2007-04-28)
     *
     * @return
     * @throws Exception
     */
    public static Date getFirstDay() {
        Calendar nowday = Calendar.getInstance();
        nowday.set(Calendar.DAY_OF_MONTH, 1);
        nowday.set(Calendar.HOUR_OF_DAY, 0);
        nowday.set(Calendar.MINUTE, 0);
        nowday.set(Calendar.SECOND, 0);
        nowday.set(Calendar.MILLISECOND, 0);
        return nowday.getTime();
    }

    /**
     * 得到前一天(如：得到是2007-04-27 今天是2007-04-28)
     *
     * @return
     * @throws Exception
     */
    public static Date getLastDay() {
        Calendar nowday = Calendar.getInstance();
        nowday.setTime(new Date());
        nowday.setTimeInMillis(nowday.getTimeInMillis() - 24 * 60 * 60 * 1000);
        return nowday.getTime();
    }

    /**
     * 得到本月的前一个月的第一天(如：得到是2007-03-01 今天是2007-04-28)
     *
     * @return
     * @throws Exception
     */
    public static Date getLastMonth() {
        Calendar nowday = Calendar.getInstance();
        nowday.setTime(new Date());
        nowday.add(Calendar.MONTH, -1);
        nowday.set(Calendar.DAY_OF_MONTH, 1);
        return nowday.getTime();
    }

    /**
     * 取得给定日期的月份的最后一天
     *
     * @param d 日期
     * @return 日期的月份的最后一天
     * @throws Exception
     */
    public static Date getLastDayByDate(Date d) {
        Calendar newday = Calendar.getInstance();
        newday.setTime(d);
        newday.add(Calendar.MONTH, 1);
        newday.set(Calendar.DATE, 1);
        newday.add(Calendar.DATE, -1);
        return newday.getTime();
    }

    /**
     * 得到本年的第一天(如：2007-01-01)
     *
     * @return
     * @throws Exception
     */
    public static Date getLastMonthFirst() {
        Calendar nowday = Calendar.getInstance();
        nowday.setTime(new Date());
        nowday.roll(Calendar.MONTH, false);
        nowday.set(Calendar.MONTH, nowday.getActualMinimum(Calendar.MONTH));
        nowday.set(Calendar.DAY_OF_MONTH, 1);
        return nowday.getTime();
    }

    /**
     * 清除日期中的"-"
     *
     * @param dateStr （如：2007-04-28）
     * @return 20070428
     */
    public static String cleanDate(String dateStr) {
        String newDate = "";
        String[] dateStrs = dateStr.split("-");
        if (dateStrs.length >= 2) {
            StringBuilder myDateTemp = new StringBuilder();
            for (int i = 0; i < dateStrs.length; i++) {
                myDateTemp.append(dateStrs[i]);
            }
            newDate = myDateTemp.toString();
        }
        return newDate;
    }

    /**
     * 时间是否在<code>days</code>内
     *
     * @param old
     * @param days
     * @return
     */
    public static boolean isDaysInterval(Date old, int days) {
        Calendar now = Calendar.getInstance();
        return (now.getTimeInMillis() - old.getTime()) <= (1000L * 3600 * 24 * days);
    }

    /**
     * 得到中文的当前星期几(如：monday = 星期一)
     *
     * @return
     */
    public static String getCurrentWeekOfChinese() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 E");
        return dateFormat.format(new Date());
    }


    /**
     * 得到<coed>calendar<code>中当前月的第一天
     *
     * @param calendar
     * @return
     */
    public static Date getFirstDayOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到当前时间,格式<hh:mm:ss>
     *
     * @return
     */
    public static String getTime() {
        StringBuffer timeBuffer = new StringBuffer();
        Calendar calendar = new GregorianCalendar();
        timeBuffer.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(calendar.get(Calendar.MINUTE)).append(":").append(
                calendar.get(Calendar.SECOND));
        return timeBuffer.toString();
    }

    /**
     * 得到当前日期前或后N月的日期(months为正数为后n月，为负数表示前n月)
     *
     * @param months 月数
     * @return
     */
    public static Date getPreviousOrNextMonthsOfDateTime(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * 得到当前日期前或后N天的日期(days为正数为后n天，为负数表示前n天)
     *
     * @param days 天数
     * @return
     */
    public static Date getPreviousOrNextDaysOfDateTime(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date getBeginOfCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 时间转换,格式为yyyy-MM-dd
     *
     * @return
     */
    public synchronized static String getDate(Date date) {
        return FORMAT_YYYY_MM_DD.format(date);
    }

    /**
     * 时间转换,格式为yyyyMMdd
     *
     * @return
     */
    public synchronized static String getDateyyyyMMdd(Date date) {
        return FORMAT_YYYYMMDD.format(date);
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd
     *
     * @return
     * @throws ParseException
     */
    public synchronized static Date getStringToDate(String datetime) throws ParseException {
        return FORMAT_YYYY_MM_DD.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd HH:MM
     *
     * @return
     * @throws ParseException
     */
    public synchronized static Date getStringToDateHm(String datetime) throws ParseException {
        return FORMAT_YYYY_MM_DD_HH_MM.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd HH:MM
     *
     * @return
     * @throws ParseException
     */
    public synchronized static Date getStringToDateyymmddHm(String datetime) throws ParseException {
        return FORMAT_YYMMDD_HH_MM.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd HH:MM:SS.SSS
     *
     * @return
     * @throws ParseException
     */
    public synchronized static Date getStringToDateHmss(String datetime) throws ParseException {
        return FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd HH:MM:SS
     *
     * @return
     * @throws ParseException
     */
    public synchronized static Date getStringToDateyyyyMMddHHmmss(String datetime) throws ParseException {
        return FORMAT_YYYY_MM_DD_HH_MM_SS.parse(datetime);
    }

    /**
     * 得到两个日期之间的天数差，包括开始和结束日期(如：beginCalender=2007-10-01，endCalendar=2007-10-20
     * 结果为：20)
     *
     * @param beginCalender 开始日期(小的)
     * @param endCalendar   结束日期(大的)
     * @return
     */
    public static long getDifferenceDays(Calendar beginCalender,
                                         Calendar endCalendar) {
        long days = (endCalendar.getTimeInMillis() - beginCalender
                .getTimeInMillis())
                / (24 * 60 * 60 * 1000);
        days = days + 1;
        return days;
    }

    /**
     * 获取一年的开始时间
     *
     * @return
     */
    public static Date getBeginDateOfCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取去年今天
     *
     * @return
     */
    public static Date getTodayOfLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        return calendar.getTime();
    }

    /**
     * 获取前年今天
     *
     * @return
     */
    public static Date getTodayOfBeforeLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 2);
        return calendar.getTime();
    }

    /**
     * 获得指标库当前操作日
     *
     * @return
     */
    public synchronized static Integer getKpiDbOpTimeDay() {
        Date date = new Date();
        return Integer.parseInt(FORMAT_YYYYMMDD.format(date));
    }

    /**
     * 获得指标库当前操作月
     *
     * @return
     */
    public synchronized static Integer getKpiDbOpTimeMonth() {
        Date date = new Date();
        return Integer.parseInt(FORMAT_YYYYMM.format(date));
    }

    /**
     * 获得指标库当前操作年
     *
     * @return
     */
    public synchronized static Integer getKpiDbOpTimeYear() {
        Date date = new Date();
        return Integer.parseInt(FORMAT_YYYY.format(date));
    }

    /**
     * @param day
     * @return
     */
    public synchronized static Integer getKpiDbAnyOffsetDayOfNow(int day) {
        Calendar nowday = Calendar.getInstance();
        nowday
                .set(Calendar.DAY_OF_YEAR, nowday.get(Calendar.DAY_OF_YEAR)
                        + day);
        return Integer.parseInt(FORMAT_YYYYMMDD.format(nowday.getTime()));
    }

    /**
     * @param currentDay
     * @return
     */
    public synchronized static Integer getCurrentDayOfPreviousMonth(int currentDay) {
        if (currentDay < 20000101) {
            return null;
        }
        try {
            Date date = FORMAT_YYYYMMDD.parse(String.valueOf(currentDay));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            return Integer.parseInt(FORMAT_YYYYMMDD.format(calendar.getTime()));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将Long 型的日期格式转换成基本格式
     *
     * @param lDate
     * @return
     * @author Jacky Yi <br>
     * @mail <br>
     * @create 2008-8-12 <br>
     * @update 2008-8-12 <br>
     * @version 1.0<br>
     * @desc <br>
     */
    public static Date longToDate(Long lDate) {
        Date d = new Date();
        d.setTime(lDate);
        return d;
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return true:同一天 false:不是同一天
     */
    public static boolean isTheSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 功能: 获取当前月份
     *
     * @return
     */
    public synchronized static Integer getYear() {
        Date date = new Date();
        return Integer.parseInt(FORMAT_YYYY.format(date));
    }

    /**
     * 功能: 获取当前月份
     *
     * @return
     */
    public synchronized static Integer getMonth() {
        Date date = new Date();
        return Integer.parseInt(FORMAT_MM.format(date));
    }

    /**
     * 功能: 获取当前月份
     *
     * @return
     */
    public synchronized static String getMonth2() {
        Date date = new Date();
        return FORMAT_MM.format(date);
    }

    /**
     * 功能: 获取日期
     *
     * @return
     */
    public synchronized static Integer getDay() {
        Date date = new Date();
        return Integer.parseInt(FORMAT_DD.format(date));
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd HH:MM，如 OCT 26 2014
     *
     * @return
     * @throws ParseException
     */
    public static Date getStringToDateMMMddyyyy(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy", Locale.US);
        return sdf.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为yyyy-MM-dd HH:MM，如 OCT 26 2014
     *
     * @return
     * @throws ParseException
     */
    public static Date getStringToDateddMMMyyyyHHmm2(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
        return sdf.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为dd-MMM-yyyy HH:mm 如：22-Jan-2014 15:00
     *
     * @return
     * @throws ParseException
     */
    public static Date getStringToDateddMMMyyyyHHmm(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.US);
        return sdf.parse(datetime);
    }

    /**
     * 时间转换,字符串转日期,格式为dd-MMM-yyyy HH:mm 如：30. Dec 2014
     *
     * @return
     * @throws ParseException
     */
    public static Date getStringToDateddMMMyyyy(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM yyyy", Locale.US);
        return sdf.parse(datetime);
    }

//	/**
//	 * 将定义的时间转换为进程调度所需要的格式
//	 * time格式为YYYY-MM-dd HH：mm:ss
//	 * @param cycle
//	 * @param time
//	 * @return
//	 * @throws Exception
//	 */
//	public static String getExpression(int cycle, String time) throws Exception {
//		try {
//			if(time.indexOf("/") != -1){
//				time = time.replace("/", "-");
//			}
//			Date date = Utils.formatStrToDate(time,Utils.FORMATTYPE2);
//			Calendar now = Calendar.getInstance();
//			now.setTime(date);
//
//			int minute  = now.get(Calendar.MINUTE);
//			int hour = now.get(Calendar.HOUR_OF_DAY);
//			int week = now.get(Calendar.DAY_OF_WEEK);
//			int day = now.get(Calendar.DAY_OF_MONTH);
//
//			switch (cycle) {
//			case Consts.JOB_CYCLE_DAY:
//				return "00 " + minute + " " + hour + " * * ?";
//			case Consts.JOB_CYCLE_MONTH:
//				return "00 " + minute + " " + hour + " "+day+" * ?";
//			case Consts.JOB_CYCLE_WEEK:
//				return "00 " + minute + " " + hour + " ? * "+week;
//			default:
//				throw new Exception("设置了错误的时间周期 : " + cycle);
//			}
//		} catch (Exception e) {
//			throw new Exception("设置了错误的时间格式 : " + time);
//		}
//	}

    /**
     * 时间转换,字符串转日期,格式为dd-MMM-yyyy HH:mm 如：30. Dec 2014
     *
     * @return
     * @throws ParseException
     */
    public static Date getAfterDate(Date curDate, int days) throws ParseException {
        return new Date(curDate.getTime() + days * 24 * 60 * 60 * 1000);
    }

    //获取某个日期的开始时间
    public static Date getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //获取某个日期的结束时间
    public static Date getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 添加日期尾部
     *
     * @param dateStr 日期字符串
     * @param isStart 是否是0点或23点
     * @return
     */
    public static String addDateTail(String dateStr, boolean isStart) {
        return isStart ? new StringBuilder(dateStr).append(" 00:00:00").toString()
                       : new StringBuilder(dateStr).append(" 23:59:59").toString();
    }

    public static void main(String[] arg) {
        String data = "2015/05/17";
        data = data.replace("/", "-");
        System.out.println(data);
    }
}
