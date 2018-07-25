package com.ybkj.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 注册拦截器 
 * Created by SYSTEM on 2017/8/16. 
 */
@SpringBootConfiguration
@Slf4j
@RestController
public class WebAppConfig extends WebMvcConfigurerAdapter  {



  /*  //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Map<String, Object> result = new HashMap<>();
                    if (e instanceof NoHandlerFoundException) {
                        log.info("接口 [" + request.getRequestURI() + "] 不存在");
                        result.put("errorCode", "404");
                        result.put("errorMsg", "页面不存在！");
                        ModelAndView mv = new ModelAndView();
                } else {
                        log.info("接口 [" + request.getRequestURI() + "]错误，请联系管理员！");
                        result.put("errorCode", "500");
                        result.put("errorMsg", "全局异常，系统错误！");
                    //result = new ResponseMsg("500", "接口 [" + request.getRequestURI() + "] 错误，请联系管理员！");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    log.error(message, e);
                }
             //   responseResult(response, result);

                return new ModelAndView();
            }

        });
    }
*/


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**").excludePathPatterns("/errorIndex");
        super.addInterceptors(registry);
    }

    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
} 