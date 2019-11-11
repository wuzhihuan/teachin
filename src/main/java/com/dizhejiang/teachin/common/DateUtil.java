package com.dizhejiang.teachin.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public class DateUtil {
    public static void main(String[] args) {
        System.out.println(chineseToDate("Mon Aug 15 11:24:39 CST 2018"));//-----2018-08-15 11:24:39
        System.out.println(numberToDate(1515299504718L));//--------2018-01-07 12:31:44
    }
    /**
     * 时间戳转换为时间格式（1515299504718）
     */
    public static String numberToDate(Long target){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long(target);
        return format.format(time);
    }

    /**
     * Mon Aug 15 11:24:39 CST 2016  转换格式
     */
    public static String chineseToDate(String date){
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate=sdf.format(newDate);
        return sDate;
    }

    /**
     * 将日期转化string
     * @param date
     * @return
     */
    public static String DateToString(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    public static String DateToString1(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = format.format(date);
        return str;
    }

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }


}
