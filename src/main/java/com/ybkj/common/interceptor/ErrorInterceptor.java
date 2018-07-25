package com.ybkj.common.interceptor;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@Controller
@Slf4j
public class ErrorInterceptor implements HandlerInterceptor {
  
  /** 
   * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在 
   * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 
   * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返 
   * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。 
   */ 
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("在Controller之前执行！");
    /**
     * 1、当用户发生请求时，如果用户的sessionId不存在，就在用户踢出，提示用户账号在被的位置的登录
     */
    if(LoginUtil.loginUserSessionIds.contains(request.getSession().getId())){
       //挤下线并跳到提示页面
      LoginUtil.loginUserSessionIds.remove("userName");
      response.sendRedirect("/static/errorpage/500.html");
    }

    return true;// 只有返回true才会继续向下执行，返回false取消当前请求
  } 
  
  /** 
   * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 
   * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操 
   * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像， 
   * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor 
   * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。 
   */ 
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
              ModelAndView modelAndView) throws Exception {
    log.info("preHandle执行结果返回正确执行！"+String.valueOf(response.getStatus()));

    /**
     * 处理发生不当的请求，获取程序代码出现异常
     */
    if(response.getStatus()== StatusCodeEnum.InternalServerErrorFail.getStatusCode()){
      //运行时异常InternalServerErrorFail
      modelAndView.setViewName("/static/errorpage/500.html");
    }else if(response.getStatus()== StatusCodeEnum.NotFoundFail.getStatusCode()){
      //找不到页面NotFoundFail
      modelAndView.setViewName("/static/errorpage/404.html");
    }else if(response.getStatus()== StatusCodeEnum.MethodNotAllowedFail.getStatusCode()){
      //请求参数异常，不支持此请求（get\post）请求中指定的方法不被允许。MethodNotAllowedFail
      modelAndView.setViewName("/static/errorpage/500.html");
    }else if(response.getStatus()==StatusCodeEnum.BadRequestFail.getStatusCode()){
      //状态码400表示：服务器未能理解请求BadRequestFail
      modelAndView.setViewName("/static/errorpage/500.html");
    }else if(response.getStatus()==StatusCodeEnum.UnsupportedMediaTypeFail.getStatusCode()){
      //状态码415表示：由于媒介类型不被支持，服务器不会接受请求。。UnsupportedMediaTypeFail
      modelAndView.setViewName("/static/errorpage/500.html");
    }




  } 
  
  /** 
   * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 
   * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
   */ 
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
      throws Exception {
    log.info("preHandle执行结果和postHandle之后返回正确执行！");
  } 
}