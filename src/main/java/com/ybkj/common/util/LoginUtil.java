package com.ybkj.common.util;




import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *@Description:  功能描述（登录工具类，采用了单一登录）
 *@Author:       刘家义
 *@CreateDate:   2018/7/25 19:23
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/25 19:23
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
public class LoginUtil {


    //用来存储需要强制下线的sessionId
    public static ArrayList<String> loginUserSessionIds=new ArrayList<>();

    //如果用户已经在线，将在线的用户的seesionId存储loginUserSessionIds，等拦截器将其下线
    public static void LoginUserSessionIds(String userName,HttpServletRequest request) throws Exception{
        String onlinedSeesionId=null;
        if(request.getServletContext().getAttribute(userName)!=null){
            onlinedSeesionId=(String)request.getServletContext().getAttribute(userName);
            //需要在此处将ServletContext域的用户资料移除，然后才能加入新的用户资料
            //也就是替换掉key为userName的value
            request.getServletContext().removeAttribute(userName);
            loginUserSessionIds.add(onlinedSeesionId);
        }
    }

}
