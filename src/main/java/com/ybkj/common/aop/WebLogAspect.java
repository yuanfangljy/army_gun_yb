package com.ybkj.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 *
 */

/**
 * Slf4j：使用的lombok插件：底层使用字节码技术 ASM 修改字节码文件，生成get和set等方法
 *@Description:  功能描述（使用aop记录日志）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:26
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:26
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Aspect
@Component
@Slf4j
public class WebLogAspect {
   // private Logger logger = LoggerFactory.getLogger(getClass());
	@Pointcut("execution(public * com.ybkj.gun.controller..*.*(..))")
	public void webLog() {
	}

	/**
	 * 使用前置通知拦截参数请求信息
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		log.info("URL : " + request.getRequestURL().toString());
		log.info("HTTP_METHOD : " + request.getMethod());
		log.info("IP : " + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			log.info("name:{},value:{}", name, request.getParameter(name));
		}
	}

	/**
	 * 后置通知
	 * @param ret
	 * @throws Throwable
	 */
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		log.info("RESPONSE : " + ret);
	}
}
