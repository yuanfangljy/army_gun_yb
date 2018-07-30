package com.ybkj.common.processingTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
	
	public static String formatStr = "yyyy-MM-dd HH:mm:ss";
	public static String formatDate = "yyyy-MM-dd";
	public static String formatDate3 = "yyyy-MM";
	public static String formatDate4 = "MM-dd";
	
	public static String formatDate1="yyyy-MM-dd HH:mm";
	public static String formatDate2="yyyy-MM-dd HH:mm";
	
	public static Date parse(String source) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		
		return sdf.parse(source);
	}
	public static Date parseByFormatDate(String source) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		
		return sdf.parse(source);
	}
	
	public static String parse(Date source) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		
		return sdf.format(source);
	}
	
	
	public static String parseDate(Date source, String format) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(source);
	}
	
	public static String parseDate(String source, String format) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat(formatStr);
		
		return sdf.format(sdf1.parse(source));
	}

	public static String getNowDate() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		
		return sdf.format(new Date());
	}

	
	public static String getNowTime() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		
		return sdf.format(new Date());
	}


	public static Date parseByFormat(String source, String formatStr) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		
		return sdf.parse(source);
	}
	
	public static String formatByFormat(Date source, String formatStr) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		
		return sdf.format(source);
	}
	
	
	public static String getWeek(Date date){
		  String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
		  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
		  Calendar calendar = Calendar.getInstance(); 
		  calendar.setTime(date); 
		  int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		  return weekDaysName[intWeek]; 
    }

	
	public static String getWeekOfDate(Date date) { 
		  String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
		  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
		  Calendar calendar = Calendar.getInstance(); 
		  calendar.setTime(date); 
		  int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		  return weekDaysCode[intWeek]; 
		}

	public static String weekNameToCode(String weekName) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		for (int i = 0; i <weekDaysName.length ; i++) {
			if(weekDaysName[i].equals(weekName)){
				return weekDaysCode[i];
			}
		}
		return null;
	}

	public static String addMinute(String time, long timeNumber) throws ParseException {
		
		Date date = new Date();
		
		if (time.length()<=10) {
			
			date = parseByFormat(time, formatDate);
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			
			cal.add(Calendar.MINUTE, Integer.parseInt(String.valueOf(timeNumber)));
			
			return DateUtil.formatByFormat(cal.getTime(), formatDate);
		}else {
			
			date = parseByFormat(time, formatStr);
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			
			cal.add(Calendar.MINUTE, Integer.parseInt(String.valueOf(timeNumber)));
			
			return DateUtil.formatByFormat(cal.getTime(), formatStr);
		}
	}
	
    /** 
     *  
     * 获取前几天的日期 
     */  
    public static Date getPrefixDate(String count){  
        Calendar cal = Calendar.getInstance();  
        int day = 0-Integer.parseInt(count)-1;  
        cal.add(Calendar.DATE,day);   // int amount   代表天数  
        Date datNew = cal.getTime();   
        //SimpleDateFormat format = new SimpleDateFormat(formatPattern);  
        return   datNew ;  
    } 
    
    
	
	
	public static Date parseFormat(String time) throws ParseException {
		
		Date date = new Date();
		
		if (time.length()<=10) {
			
			date = parseByFormat(time, formatDate);
			
			return date;
		}else {
			
			date = parseByFormat(time, formatStr);
			
			return date;
		}
	}
	
	public static void main(String args[]){
		//System.out.println(getWeek(new Date()));;
	}
	
	//由出生日期获得年龄  
	public static Map<String,Long> parseFormatGetAge(String birthDay) throws Exception{
		
		if(birthDay.length()==7){
			//只有年月
			
			return getAge(DateUtil.parseByFormat(birthDay, formatDate3));
		}else{
			//年月日
			return getAge(DateUtil.parseByFormat(birthDay, formatDate));
		}
		
		
	}
	
	
    public static  Map<String,Long> getAge(Date birthDay) throws Exception {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;
        int month = 0;
        Map<String,Long> map = new HashMap<String,Long>();
        
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth){
                	age--;  
                }
            }else{  
                age--;  
            }  
        }
        
        
        if(monthNow>= monthBirth){
        	month =monthNow- monthBirth;
        	if(dayOfMonthNow < dayOfMonthBirth){
        		month--;
        	}
        }else{
        	month = monthBirth+11 - monthNow;
        	if(dayOfMonthNow < dayOfMonthBirth){
        		month--;
        	}
        }
        
        map.put("age", Long.parseLong(age+""));
        map.put("month", Long.parseLong(month+""));
        
        
        return map;  
    }
    
  //判断选择的日期是否是本周  
    public static boolean isThisWeek(long time)  
    {  
        Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if(paramWeek==currentWeek){  
           return true;  
        }  
        return false;  
    }

	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		}
		return convertSuccess;
	}
	
	public static String parseDateString(String dateStr){
		if(dateStr!=null&&!DateUtil.isValidDate(dateStr)){
			SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
			try {
				Date date = sdf1.parse(dateStr);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sDate = sdf.format(date);
				return sDate;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateStr;
	}

	public static int nowDateIndex(List<String> dates){
		int nextIndex=0;
		int preIndex=0;
		try {
			Date nowDate=DateUtil.parseByFormatDate(getNowDate());
			Date nextDate=null;
			Date preDate=null;
			for(int i=0;i<dates.size();i++){
				String date=dates.get(i);
				Date date1=DateUtil.parseByFormatDate(date);
				//当天优先返回
				if(date1.getTime()==nowDate.getTime()){
					return i;
				}else if(date1.getTime()>nowDate.getTime()){
					//取当天下一个时间的索引
					if(nextDate==null||nextDate.getTime()>date1.getTime()){
						nextIndex=i;
						nextDate=date1;
					}
				}else if(date1.getTime()<nowDate.getTime()){
					//取当天上一个时间的索引
					if(preDate==null||preDate.getTime()<date1.getTime()){
						preIndex=i;
						preDate=date1;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//没有当天的时间 默认选下一节点优先
		return nextIndex==0?nextIndex:preIndex;
	}

	public static boolean isBirthdayDateFormat(String date){
		if(date==null||"".equals(date)){
			return false;
		}
		try
		{
			if(date.length()==10){
				parseByFormat(date,formatDate);
				return true;
			}
			if(date.length()==7){
				parseByFormat(date,formatDate3);
				return true;
			}
		} catch (ParseException e)
		{
			return false;
		}
		return false;
	}
}
