package com.ybkj.common.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
@Component
public class DataTool {
    public static String formatStr = "yyyy-MM-dd HH:mm:ss";
    public static String formatDate = "yyyy-MM-dd";
    public static String formatDate3 = "yyyy-MM";
    public static String formatDate4 = "MM-dd";

    public static String formatDate1="yyyy-MM-dd HH:mm";
    public static String formatDate2="yyyy-MM-dd HH:mm";

    public static String formatDate5 = "yyyyMMddhhmmss";
    /**
     * 将字符串转换成时间
     * @param time
     * @return
     */
    public static Date stringToDate(String time) throws ParseException {
        Date date=null;
        SimpleDateFormat formatter=null;
        System.out.println("---"+time.length());
        if(time.length()==10){
            formatter=new SimpleDateFormat(formatDate);
        }else{
            formatter=new SimpleDateFormat(formatStr);
        }

        date=formatter.parse(time);
        return date;
    }

    /**
     * 将时间转换成字符串
     * @return
     */
    public static String  dateToString() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate5);
        String sendTime = sdf.format(new Date());
        return sendTime;
    }
    /**
     * 将时间格式：yyyy-MM-dd  转成 yyyyMMdd
     *            yyyy-MM-dd HH:mm:ss   转成   yyyyMMddHHmmss
     * @return
     */
    public static String  dateToString(Date time) throws ParseException {
        SimpleDateFormat sdf =null;
                sdf= new SimpleDateFormat(formatDate5);
        String format = sdf.format(time);
        if(format.length()==10){
            sdf=new SimpleDateFormat(formatDate);
        }else{
            sdf=new SimpleDateFormat(formatStr);
        }
        return format;
    }





    /** 年-月-日 时:分:秒 显示格式 */
    // 备注:如果使用大写HH标识使用24小时显示格式,如果使用小写hh就表示使用12小时制格式。
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 年-月-日 显示格式 */
    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

    private static SimpleDateFormat simpleDateFormat;

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String DateToString(Date source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }

    /**
     *
     * unix时间戳转为指定格式的String类型
     *
     *
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeStampToString(long source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期转换为时间戳(unix时间戳,单位秒)
     *
     * @param date
     * @return
     */
    public static long dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }

    /**
     *
     * 字符串转换为对应日期(可能会报错异常)
     *
     * @param source
     * @param pattern
     * @return
     * @throws java.text.ParseException
     */
    public static Date stringToDate(String source, String pattern) throws java.text.ParseException {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        date = simpleDateFormat.parse(source);
        return date;
    }

    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     *
     * @methodDesc: 功能描述:(获取当前系统时间戳)
     * @param: @return
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }

}
