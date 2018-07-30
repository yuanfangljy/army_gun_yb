package com.ybkj.common.interceptor;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@SuppressWarnings("all")
@Controller
@Slf4j
public class ErrorInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("在Controller之前执行！");
        //获取判定登陆的session是否存在
       // String token = (String) request.getSession().getAttribute("token");
        String userName = (String) request.getSession().getAttribute("userName");
        if(userName == null){
            String XRequested =request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(XRequested)){
                response.getWriter().write("IsAjax");
            }else{
                response.sendRedirect("/gun/statics/errorpage/500.html");
            }
            return false;
        }
        if(userName == null || userName == ""){
            String XRequested =request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(XRequested)){
                response.getWriter().write("IsAjax");
            }else{
                response.sendRedirect("/gun/statics/errorpage/500.html");
            }
            return false;
        }

    /*    String userName = (String) request.getSession().getAttribute("userName");
        //获取用户的session
       System.out.println(userName+"-------------------------------------");
        if(userName==null){
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
           String url= basePath+"statics/errorpage/500.html";
            System.out.println(url);
            BaseModel baseModel = new BaseModel();
            baseModel.setErrorMessage("您还没有登录，请登录后再试！");
            baseModel.setStatus(StatusCodeEnum.FIELD_FAIL.getStatusCode());
            // 返回给前端页面的未登陆标识
            PrintWriter out=response.getWriter();
            out.print(baseModel);
            out.flush();
            out.close();
//           response.sendRedirect(url);

            return  false;
        }*/
        /**
         * 1、当用户发生请求时，如果用户的sessionId不存在，就在用户踢出，提示用户账号在被的位置的登录
         */
   if(LoginUtil.loginUserSessionIds.contains(request.getSession().getId())){
       //挤下线并跳到提示页面
       String path = request.getContextPath();
      LoginUtil.loginUserSessionIds.remove("userName");
      response.sendRedirect(path+"/statics/errorpage/500.html");
    }

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
        log.info("preHandle执行结果返回正确执行！" + String.valueOf(response.getStatus()));

        /**
         * 处理发生不当的请求，获取程序代码出现异常
         */
        if (modelAndView != null) {


            if (response.getStatus() == StatusCodeEnum.InternalServerErrorFail.getStatusCode()) {
                //运行时异常InternalServerErrorFail
                modelAndView.setViewName("/static/errorpage/500.html");
            } else if (response.getStatus() == StatusCodeEnum.NotFoundFail.getStatusCode()) {
                //找不到页面NotFoundFail
                modelAndView.setViewName("/static/errorpage/404.html");
            } else if (response.getStatus() == StatusCodeEnum.MethodNotAllowedFail.getStatusCode()) {
                //请求参数异常，不支持此请求（get\post）请求中指定的方法不被允许。MethodNotAllowedFail
                modelAndView.setViewName("/static/errorpage/500.html");
            } else if (response.getStatus() == StatusCodeEnum.BadRequestFail.getStatusCode()) {
                //状态码400表示：服务器未能理解请求BadRequestFail，可能是字符长度问题
                modelAndView.setViewName("/static/errorpage/500.html");
            } else if (response.getStatus() == StatusCodeEnum.UnsupportedMediaTypeFail.getStatusCode()) {
                //状态码415表示：由于媒介类型不被支持，服务器不会接受请求。。UnsupportedMediaTypeFail
                modelAndView.setViewName("/static/errorpage/500.html");
            }
        }

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
 /* @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
      throws Exception {
    log.info("preHandle执行结果和postHandle之后返回正确执行！");
  }*/
}