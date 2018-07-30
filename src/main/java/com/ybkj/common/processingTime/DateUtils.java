/*
package com.ybkj.common.processingTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import bbcx.common.bean.DayOfWeek;

*/
/**
 * 日期工具类 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 * 
 *//*

public final class DateUtils {
    */
/**
     * 英文简写（默认）如：2010-12-01
     *//*

    public static String FORMAT_SHORT = "yyyy-MM-dd";
    */
/**
     * 英文全称 如：2010-12-01 23:15:06
     *//*

    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    */
/**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     *//*

    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    */
/**
     * 中文简写 如：2010年12月01日
     *//*

    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    */
/**
     * 中文全称 如：2010年12月01日 23时15分06秒
     *//*

    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    */
/**
     * 精确到毫秒的完整中文时间
     *//*

    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


    */
/**
     * 获得默认的 date pattern
     *//*

    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    */
/**
     * 根据预设格式返回当前日期
     * 
     * @return
     *//*

    public static String getNow() {
        return format(new Date());
    }

    */
/**
     * 根据用户格式返回当前日期
     * 
     * @param format
     * @return
     *//*

    public static String getNow(String format) {
        return format(new Date(), format);
    }

    */
/**
     * 使用预设格式格式化日期
     * 
     * @param date
     * @return
     *//*

    public static String format(Date date) {
        return format(date, getDatePattern());
    }
    
    */
/**
     * 使用预设格式格式化日期
     * 
     * @param date
     * @return
     *//*

    public static String formatShortDate(Date date) {
        return format(date,FORMAT_SHORT);
    }

    */
/**
     * 使用用户格式格式化日期
     * 
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return
     *//*

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    */
/**
     * 使用预设格式提取字符串日期
     * 
     * @param strDate
     *            日期字符串
     * @return
     *//*

    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }
    
    public static Date parseShortDate(String strDate) {
        return parse(strDate, FORMAT_SHORT);
    }

    */
/**
     * 使用用户格式提取字符串日期
     * 
     * @param strDate
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     *//*

    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
     * 在日期上增加数个整月
     * 
     * @param date
     *            日期
     * @param n
     *            要增加的月数
     * @return
     *//*

    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    */
/**
     * 在日期上增加天数
     * 
     * @param date
     *            日期
     * @param n
     *            要增加的天数
     * @return
     *//*

    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    */
/**
     * 获取时间戳
     *//*

    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    */
/**
     * 获取日期年份
     * 
     * @param date
     *            日期
     * @return
     *//*

    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }
    
    */
/**
     * 获取日期月份
     * 
     * @param date
     *            日期
     * @return
     *//*

    public static String getMonth(Date date) {
        return format(date).substring(5, 7);
    }

    */
/**
     * 按默认格式的字符串距离今天的天数
     * 
     * @param date
     *            日期字符串
     * @return
     *//*

    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    */
/**
     * 按用户格式字符串距离今天的天数
     * 
     * @param date
     *            日期字符串
     * @param format
     *            日期格式
     * @return
     *//*

    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }
    
    public static  int getMondayPlus() {  
        Calendar cd = Calendar.getInstance();  
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);  
        if (dayOfWeek == 1) {  
            return -6;  
        } else {  
            return 2 - dayOfWeek;  
        }  
    }

    public static  int getMondayPlus(Calendar cd) {
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得当前周- 周一的日期  
    public static  String getCurrentMonday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();  
        currentDate.add(GregorianCalendar.DATE, mondayPlus);  
        Date monday = currentDate.getTime();  
        format(new Date(), FORMAT_SHORT);
//        DateFormat df = DateFormat.getDateInstance();  
//        String preMonday = df.format(monday);  
        String preMonday = dateFormat.format(monday);
        return preMonday;  
    }  
    
    public static String getFirstDayOfWeek(String date){
    	try {
			return getDayOfWeek(date).getWeekFirstDay();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    // 获得当前周- 周一的日期  
    public static  String getCurrentDate() {  
         
        return format(new Date(), FORMAT_SHORT);  
    }

    */
/**
     * 获取周一日期
     * @param date
     * @return
     *//*

    public static String getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return format(cal.getTime(),FORMAT_SHORT);
    }


    */
/**
     * 获取以一周中第几天为循环开始时间的开始时间
     * @param dayNum
     * @return
     *//*

    public static String getLoopDay(int dayNum){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(dayNum+1);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day<(dayNum+1)){
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7);
        }else{
            // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        }
        return format(cal.getTime(),FORMAT_SHORT)+" 00:00:00";
    }

    */
/**
     * 获取指定时间内的周几
     * @param dayNum
     * @return
     *//*

    public static String getLoopDayTest(int dayNum,String dateTest) throws Exception{
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(sdf.parse(dateTest));
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(dayNum+1);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day<(dayNum+1)){
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7);
        }else{
            // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        }
        return format(cal.getTime(),FORMAT_SHORT)+" 00:00:00";
    }

    // 获得当前周- 周日  的日期  
    public static String getPreviousSunday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();  
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);  
        Date monday = currentDate.getTime();  
//        DateFormat df = DateFormat.getDateInstance();  
//        String preMonday = df.format(monday);  
        String preMonday = dateFormat.format(monday);
        return preMonday;  
    }
    
    */
/**
    * 获得当前月--开始日期  
    * @return
    *//*

    public static String getMinMonthDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();
        try {  
            calendar.setTime(dateFormat.parse(date));  
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
            return dateFormat.format(calendar.getTime());  
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    */
/**
     * 获得当前月--结束日期  
     * @return
     *//*

    public static String getMaxMonthDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();  
        try {  
            calendar.setTime(dateFormat.parse(date));  
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
            return dateFormat.format(calendar.getTime());  
        }  catch (ParseException e) {
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    
    */
/**
     * 获得当前月--开始日期  
     * @return
     *//*

    public static String getMinMonthDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
		return dateFormat.format(calendar.getTime());  

    }  
  
    */
/**
     * 获得当前月--结束日期  
     * @return
     *//*

    public static String getMaxMonthDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return dateFormat.format(calendar.getTime());  
    }  
    
    */
/**
     * 获取前半年日期
     * @return
     *//*

    public static String getHalfYearAgo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.MONTH, -6);
		return dateFormat.format(calendar.getTime());  
    }  
    


    */
/**
     * 获取一年前日期
     * @return
     *//*

    public static String getOneYearAgo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.YEAR, -1);
		return dateFormat.format(calendar.getTime());  
    }
    */
/**
     * 获取两年前日期
     * @return
     *//*

    public static String getTwoYearAgo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.YEAR, -2);
		return dateFormat.format(calendar.getTime());  
    }


    */
/**
     * 获取时间
     *  
     * @return 
     *//*

    public static Date getTimes(int year,int month,int day) {  
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.YEAR, year);  
        cal.set(Calendar.MONTH, month-1);  
        cal.set(Calendar.DATE, day);  
      //  cal.set(Calendar.HOUR_OF_DAY, hour);  
      //  cal.set(Calendar.SECOND, 0);  
      //  cal.set(Calendar.MINUTE, minute);  
      //  cal.set(Calendar.MILLISECOND, 0);  
        return  cal.getTime();  
    }
    
    */
/**
     * 获取时间
     *  
     * @return 
     *//*

    public static String getTimesStr(int year,int month,int day) {  
        
        return  formatShortDate(getTimes(  year,  month,  day));  
    }

    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus(Date gmtCreate) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(gmtCreate);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    // 获得下周星期一的日期
    public static Date getNextMonday(Date gmtCreate) {
        int mondayPlus = getMondayPlus(gmtCreate);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();
        return monday;
    }

    // 获得下周星期一的日期
    public static Date getNextMonday() {
        int mondayPlus = getMondayPlus(new Date());
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();
        return monday;
    }
    
    */
/**
     * 根据日期获取当前自然周的信息
     * @param date 格式 yyyy-MM-dd
     * @return
     * @throws Exception
     *//*

    public static DayOfWeek getDayOfWeek(String date) throws Exception {  
        // String date = "2013-09";  
		DayOfWeek dayOfWeek = new DayOfWeek();
		
		String vMonth =date.substring(0,7);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");  
        Date date1 = dateFormat.parse(vMonth);  
        Calendar calendar = new GregorianCalendar();  
        calendar.setTime(date1);  
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  
        ////System.out.println("days:" + days);  
        int count = 0;  
        dayOfWeek.setDayStr(date);
        for (int i = 1; i <= days; i++) {  
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");  
            Date date2 = dateFormat1.parse(vMonth + "-" + i);  
            calendar.clear();  
            calendar.setTime(date2); 
            
            int k = calendar.get(Calendar.DAY_OF_WEEK);
            if (k == 1) {// 若当天是周日  
                count++;  
                ////System.out.println("-----------------------------------");  
                ////System.out.println("第" + count + "周");  
                dayOfWeek.setWeekOfMonth(count);
                if (i - 6 <= 1) {  
                    ////System.out.println("本周开始日期:" + vMonth + "-"+ ""+ ""+ "" + 1); 
                    dayOfWeek.setWeekFirstDay(vMonth+ "-"+String.format("%02d", i));
                } else {  
                    ////System.out.println("本周开始日期:" + vMonth + "-" + (i - 6));  
                    dayOfWeek.setWeekFirstDay(vMonth+ "-"+String.format("%02d", i-6));
                }  
                ////System.out.println("本周结束日期:" + vMonth + "-" + i);  
                dayOfWeek.setWeekLastDay(vMonth+ "-"+String.format("%02d", i));
                ////System.out.println("-----------------------------------");  
            }  
            if (k != 1 && i == days) {// 若是本月最好一天，且不是周日  
                count++;  
                ////System.out.println("-----------------------------------");  
                ////System.out.println("第" + count + "周");  
                dayOfWeek.setWeekOfMonth(count);
                ////System.out.println("本周开始日期:" + vMonth + "-" + (i - k + 2));  
                dayOfWeek.setWeekFirstDay(vMonth+ "-"+String.format("%02d", i - k + 2));
                ////System.out.println("本周结束日期:" + vMonth + "-" + i);  
                dayOfWeek.setWeekLastDay(vMonth+ "-"+String.format("%02d", i));
                ////System.out.println("-----------------------------------");  
            }  
            //dayOfWeek.print();
            if(dayOfWeek.checkDayOfWeek()){
            	 dayOfWeek.print();
            	return dayOfWeek;
             
            }
        }
        dayOfWeek.print();
        return dayOfWeek;
    }

    */
/**
     * 根据时间获取此时间的前num天（负数）或后num（正数）天
     * @param num
     * @return
     *//*

    public static String getNextNumDays(int num,String dateTime){
       Date date = parseShortDate(dateTime);
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(Calendar.DAY_OF_WEEK,num);
       return format(cal.getTime(),FORMAT_SHORT)+" 00:00:00";
    }
    
    
    public static void main(String args[]){
        //System.out.println(getLoopDay(3));
        //System.out.println(DateUtils.getNextNumDays(7,getLoopDay(2)));
        ////System.out.println(getCurrentMonday(  ));
    	
    }

    */
/**
     * 判断日期是否在区间内  【左闭右开】
     * @param dateStart
     * @param dateEnd
     * @param compareDate
     * @return
     *//*

    public static boolean jugdeInThisRange(Date dateStart,Date dateEnd,Date compareDate){
        boolean flag = false;
        //此处时间的结束时间应该是开区间，不包括周三（暂时只有开班调用了）
        if(compareDate.getTime()>=dateStart.getTime() && dateEnd.getTime()>compareDate.getTime()){
            flag = true;

        }
        return flag;
    }

    */
/**
     * 获取下礼拜二
     * @return
     *//*

    public static String getNextTuesday(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

        return dateFormat.format(cal.getTime());
    }

    */
/**
     * 获取下礼拜三
     * @return
     *//*

    public static String getNextWednesday(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

        return dateFormat.format(cal.getTime());
    }

    */
/**
     * 获取下礼拜二
     * @return
     *//*

    public static String getNextTuesday(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

        return dateFormat.format(cal.getTime());
    }

    */
/**
     * 获取下礼拜三
     * @return
     *//*

    public static String getNextWednesday(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

        return dateFormat.format(cal.getTime());
    }

    */
/**
     * 获取当前周几
     * @return
     *//*

    public static int getWeekday(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    */
/**
     * 获取课节循环周二时间
     * @return
     *//*

    public static String getWednesday(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.formatDate);
        Calendar cal = Calendar.getInstance();
        int weekday=getWeekday();
        if(weekday<4){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        }else{
            cal.add(Calendar.WEEK_OF_YEAR, 1);
        }
        return dateFormat.format(cal.getTime());
    }
}*/
