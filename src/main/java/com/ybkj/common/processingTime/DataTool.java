package com.ybkj.common.processingTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTool {

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
            formatter=new SimpleDateFormat("yyyy-MM-dd");
        }else{
            formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        date=formatter.parse(time);
        return date;
    }
}
