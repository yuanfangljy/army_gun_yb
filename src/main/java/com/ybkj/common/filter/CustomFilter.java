package com.ybkj.common.filter;

import com.ybkj.common.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *@Description:  功能描述（自定义过滤器）
 *@Author:       刘家义
 *@CreateDate:   2018/8/14 16:00
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/8/14 16:00
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Slf4j
@Component
//说明这是一个web过滤器，它拦截的url为/customFilter，过滤器名字为blogsTest
@WebFilter(filterName="customFilter",urlPatterns= {"/*"})
//@Order中的value越小，优先级越高。
@Order(value = 1)
public class CustomFilter implements Filter{



    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    //不需要登录就可以访问的路径(比如:注册登录等)
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/gun/static/login.html","/gun/static/css/bootstrap.min.css","/gun/static/css/loginForm.css","/gun/static/js/jquery-1.4.2.min.js","/gun/static/img/yonghu.png","/gun/static/img/pass.png","/gun/static/layui/layui.all.js","/gun/static/img/beijing.png","/gun/webUser/webUserLogin","/gun/static/errorpage/500.html")));

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/gun/static/login.html","/gun/static/css/*","/gun/static/js/*","/gun/static/img/*","/gun/static/layui/*"};

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 是过滤器的核心
     * 1、在实现接口方法之后，我们要转换request和response类型至HttpServlet，否则接下去的操作可能会报错。
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);
        if(!needFilter){//不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        }else if(LoginUtil.loginUserSessionIds.contains(request.getSession().getId())){
            //挤下线并跳到提示页面
            String path = request.getContextPath();
            LoginUtil.loginUserSessionIds.remove(request.getSession().getId());
            session.removeAttribute("userName");
            //输出到页面
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out=response.getWriter();
            out.print("<script>");
            out.print("alert('该账号已在异地登录，您当前被挤下，可重新登录');");
            out.println("window.location.href='login.html'");
            //out.println("history.back();");
            out.print("</script>");
            out.flush();
            out.close();

            log.info("----------不要意思您的账号已在异地登录，请及时修改密码---------");
            return ;
           // response.sendRedirect(path+"/static/errorpage/500.html");
        }else{//需要过滤器
            // session中包含user对象,则是登录状态
            if(session!=null&&session.getAttribute("userName")!= null){
                // System.out.println("user:"+session.getAttribute("user"));
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    System.out.println("------过滤器-----"+request.getContextPath());
                    response.sendRedirect(request.getContextPath()+"/static/login.html");
                }
                return;
            }
        }
    }

    /**
     * 摧毁
     */
    @Override
    public void destroy() {

    }


    /**
     * 是否需要过滤
     * @param uri
     * @return
     */
    public boolean isNeedFilter(String uri) {
        for (String includeUrl : ALLOWED_PATHS) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }
}
