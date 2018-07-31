package com.ybkj.common.util;

import org.springframework.stereotype.Component;

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

}
