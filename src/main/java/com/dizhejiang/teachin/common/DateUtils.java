package com.dizhejiang.teachin.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

//import java.time.Period;

public class DateUtils {
    private static Log log = LogFactory.getLog(DateUtil.class);
//    private static final long ONE_MINUTE = 60L;
//    private static final long ONE_HOUR = 3600L;
//    private static final long ONE_DAY = 86400L;
//    private static final long ONE_MONTH = 2592000L;
//    private static final long ONE_YEAR = 31104000L;
    public static final String DEFAULT_MONTH_PATTERN = "yyyy-MM";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String[] DAY_OF_WEEK_NAMES = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    public static final String[] QUARTER_OF_YEAR_NAMES = new String[]{"一季度", "二季度", "三季度", "四季度"};
    public static final String[] MONTH_OF_YEAR_NAMES = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    public DateUtils() {
    }

//    public static void main(String[] args) {
//        System.out.println(getSunday(getSysDate()));
//    }

    public static int getYear(Date inputDate) {
        DateTime dateTime = new DateTime(inputDate.getTime());
        return dateTime.year().get();
    }

    public static int getDayOfMonth(Date inputDate) {
        DateTime dateTime = new DateTime(inputDate.getTime());
        return dateTime.dayOfMonth().get();
    }

    public static int getMonthOfYear(Date inputDate) {
        DateTime dateTime = new DateTime(inputDate.getTime());
        return dateTime.monthOfYear().get();
    }

    public static String getMonthOfYearName(Date inputDate) {
        return MONTH_OF_YEAR_NAMES[getMonthOfYear(inputDate) - 1];
    }

    public static int getWeekOfYear(Date inputDate) {
        DateTime dateTime = new DateTime(inputDate.getTime());
        return dateTime.weekOfWeekyear().get();
    }

    public static int getDayOfWeek(Date inputDate) {
        DateTime dateTime = new DateTime(inputDate.getTime());
        return dateTime.dayOfWeek().get();
    }

    public static String getDayOfWeekName(Date inputDate) {
        return DAY_OF_WEEK_NAMES[getDayOfWeek(inputDate) - 1];
    }

    public static int getQuarterOfYear(Date inputDate) {
        int month = getMonthOfYear(inputDate);
        return (month - 1) / 3 + 1;
    }

    public static String getQuarterOfYearName(Date inputDate) {
        return QUARTER_OF_YEAR_NAMES[getQuarterOfYear(inputDate) - 1];
    }

//    public static String getDateTimeBetween(Date startDate, Date endDate) {
//        DateTime start = new DateTime(startDate.getTime());
//        DateTime end = new DateTime(endDate.getTime());
//        Period period = new Period(start, end);
//        String result = period.getDays() + "天" + period.getHours() + "小时" + period.getMinutes() + "分" + period.getSeconds() + "秒";
//        return result;
//    }
//
//    public static int getDaysBetween(Date startDate, Date endDate) {
//        DateTime start = new DateTime(startDate.getTime());
//        DateTime end = new DateTime(endDate.getTime());
//        Period period = new Period(start, end);
//        return period.getDays();
//    }

    public static int getDateTimeMinuteBetween(Date startDate, Date endDate) {
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        Minutes minutes = Minutes.minutesBetween(start, end);
        return minutes.getMinutes();
    }

    public static Date getMonday(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(7, 2);
        return cDay.getTime();
    }

    public static Date getSunday(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(7, 7);
        return cDay.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(5, 1);
        System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(5, cDay.getActualMaximum(5));
        System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    public static Date getFirstDayOfQuarter(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int curMonth = cDay.get(2);
        if (curMonth >= 0 && curMonth <= 2) {
            cDay.set(2, 0);
        }

        if (curMonth >= 3 && curMonth <= 5) {
            cDay.set(2, 3);
        }

        if (curMonth >= 6 && curMonth <= 7) {
            cDay.set(2, 6);
        }

        if (curMonth >= 9 && curMonth <= 11) {
            cDay.set(2, 9);
        }

        cDay.set(5, cDay.getActualMinimum(5));
        System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    public static Date getLastDayOfQuarter(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int curMonth = cDay.get(2);
        if (curMonth >= 0 && curMonth <= 2) {
            cDay.set(2, 2);
        }

        if (curMonth >= 3 && curMonth <= 5) {
            cDay.set(2, 5);
        }

        if (curMonth >= 6 && curMonth <= 7) {
            cDay.set(2, 7);
        }

        if (curMonth >= 9 && curMonth <= 11) {
            cDay.set(2, 11);
        }

        cDay.set(5, cDay.getActualMaximum(5));
        System.out.println(cDay.getTime());
        return cDay.getTime();
    }

    public static Date plusSysDays(int days) {
        return plusDays(getSysDate(), days);
    }

    public static Date plusDays(Date input, int days) {
        DateTime dateTime = new DateTime(input.getTime());
        return dateTime.plusDays(days).toDate();
    }

    public static Date plusYears(Date input, int years) {
        DateTime dateTime = new DateTime(input.getTime());
        return dateTime.plusYears(years).toDate();
    }

    public static Date plusMonths(Date input, int months) {
        DateTime dateTime = new DateTime(input.getTime());
        return dateTime.plusMonths(months).toDate();
    }

    public static Date getSysDateNoPattern() {
        return new Date(System.currentTimeMillis());
    }

    public static Timestamp getSysTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date getSysDate() {
        return stringToDate(getSysDateTimeString(), DEFAULT_DATE_PATTERN);
    }

    public static Date getSysDateTime() {
        return stringToDate(getSysDateTimeString(), DEFAULT_DATETIME_PATTERN);
    }

    public static Date getSysDate(String pattern) {
        return stringToDate(getSysDateTimeString(), pattern);
    }

    public static String getSysDateString() {
        return dateToString(getSysDateNoPattern(), DEFAULT_DATE_PATTERN);
    }

    public static String getSysMonth() {
        return getSysDateString(DEFAULT_MONTH_PATTERN);
    }

    public static String getSysDateString(String pattern) {
        return dateToString(getSysDateNoPattern(), pattern);
    }

    public static String getSysDateTimeString() {
        return dateToString(getSysDateNoPattern(), DEFAULT_DATETIME_PATTERN);
    }

    public static String getSysDateTimeString(String pattern) {
        return dateToString(getSysDateNoPattern(), pattern);
    }

    public static final String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATE_PATTERN);
    }

    public static final String timeToString(Date date) {
        return dateToString(date, DEFAULT_DATETIME_PATTERN);
    }

    public static final String dateToString(Date date, String pattern) {
        return (new SimpleDateFormat(pattern)).format(date);
    }

    public static final Date stringToDate(String dateStr) {
        return stringToDate(dateStr, DEFAULT_DATE_PATTERN);
    }

    public static final Date stringToDateTime(String dateStr) {
        return stringToDate(dateStr, DEFAULT_DATETIME_PATTERN);
    }

    public static final Date getSysDateNoTime() {
        String datestr = dateToString(getSysDateNoPattern(), DEFAULT_DATE_PATTERN);
        return stringToDate(datestr, DEFAULT_DATE_PATTERN);
    }

    public static String formatTimeSpan(long span) {
        long minseconds = span % 1000L;
        span /= 1000L;
        long seconds = span % 60L;
        span /= 60L;
        long mins = span % 60L;
        span /= 60L;
        long hours = span % 24L;
        span /= 24L;
        return (new Formatter()).format("%1$dDay %2$02d:%3$02d:%4$02d.%5$03d", span, hours, mins, seconds, minseconds).toString();
    }

    public static final Date stringToDate(String dateStr, String pattern) {
        try {
            return (new SimpleDateFormat(pattern)).parse(dateStr);
        } catch (ParseException var3) {
            log.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        }
    }

    public static String getFromDateToNow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long time = date.getTime() / 1000L;
        long now = (new Date()).getTime() / 1000L;
        long ago = now - time;
        if (ago <= 3600L) {
            return ago / 60L + "分钟前";
        } else if (ago < 1L) {
            return "刚刚";
        } else if (ago <= 86400L) {
            return ago / 3600L + "小时" + ago % 3600L / 60L + "分钟前";
        } else if (ago <= 172800L) {
            return "昨天" + calendar.get(11) + "点" + calendar.get(12) + "分";
        } else if (ago <= 259200L) {
            return "前天" + calendar.get(11) + "点" + calendar.get(12) + "分";
        } else {
            long year;
            if (ago <= 2592000L) {
                year = ago / 86400L;
                return year + "天前" + calendar.get(11) + "点" + calendar.get(12) + "分";
            } else if (ago <= 31104000L) {
                year = ago / 2592000L;
                long day = ago % 2592000L / 86400L;
                return year + "个月" + day + "天前" + calendar.get(11) + "点" + calendar.get(12) + "分";
            } else {
                year = ago / 31104000L;
                int month = calendar.get(2) + 1;
                return year + "年前" + month + "月" + calendar.get(5) + "日";
            }
        }
    }

    public static String getTodayToDeadline(Date date) {
        long deadline = date.getTime() / 1000L;
        long now = (new Date()).getTime() / 1000L;
        long remain = deadline - now;
        if (remain <= 3600L) {
            return "只剩下" + remain / 60L + "分钟";
        } else if (remain <= 86400L) {
            return "只剩下" + remain / 3600L + "小时" + remain % 3600L / 60L + "分钟";
        } else {
            long day = remain / 86400L;
            long hour = remain % 86400L / 3600L;
            long minute = remain % 86400L % 3600L / 60L;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }
    }
    
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (null == format) {
			format = "yyyy-MM-dd";
		}
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}
    
    
    /**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd");
	}


    /**
     * 获取当前日期yyMMdd格式的字符串
     * @return yyMMdd
     */
    public static String getMMdd() {
        String sysDateString = getSysDateString();
        return sysDateString.replace("-","").substring(4, sysDateString.length());
    }

    public static String getCurrentTimeSS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        String now = sdf.format(date);
        return now;
    }
    /**
     * 获取当前年月（格式：yyyyMM）
     *
     * @return
     */
    public static String getCurrentYearMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String now = sdf.format(date);
        return now;
    }


    public static void main(String[] args){
       // System.out.println(getFromDateToNow(stringToDate("2019-09-05 17:41:23")));
        //System.out.println(getSysDateTimeString());
    }

}
