package com.ybkj.common.util;




import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
@Component
public class LoginUtil {


    //用来存储需要强制下线的sessionId
    //使用单例模式进行存储
    public static ArrayList<String> loginUserSessionIds =new ArrayList<String>();
  /*//如果是private就不能被反射
    public LoginUtil(){
    }
    *//**
     * 懒汉式当你需要的时候，我才会被实例化，之后都用一个实例
     * 当多个线程，使用这个实例的时候，就会创建多个，会出现线程安全的问题。
     * @return
     *//*
    public ArrayList<String> getSingletonUser(){
        if(null==loginUserSessionIds){
            synchronized (LoginUtil.class){
                loginUserSessionIds=new ArrayList<String>();
            }
        }
        return loginUserSessionIds;
    }*/

    //如果用户已经在线，将在线的用户的seesionId存储loginUserSessionIds，等拦截器将其下线
    public static void LoginUserSessionIds(String userName) throws Exception{
        String onlinedSeesionId=null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getServletContext().getAttribute(userName)!=null){
            onlinedSeesionId=(String)request.getServletContext().getAttribute(userName);
            //需要在此处将ServletContext域的用户资料移除，然后才能加入新的用户资料
            //也就是替换掉key为userName的value
            request.getServletContext().removeAttribute(userName);
            loginUserSessionIds.add(onlinedSeesionId);
        }
    }

}
