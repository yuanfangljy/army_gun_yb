package com.ybkj.common.aop;

import com.ybkj.common.annotation.Token;
import com.ybkj.common.model.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.UUID;

/**
 *@Description:  功能描述（出入库mq切面方法）
 *@Author:       刘家义
 *@CreateDate:   2018/7/31 9:19
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/31 9:19
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@SuppressWarnings("all")
@Aspect
@Component
@Slf4j
@Transactional
public class MessageAspect {

    @Pointcut("@annotation(com.ybkj.common.annotation.Token)")
    public void cut() {
    }

    @Before(value = "cut() && @annotation(token)")
    public void testToken(final JoinPoint joinPoint, Token token){
        try {
            if (token != null) {
                //获取 joinPoint 的全部参数
                Object[] args = joinPoint.getArgs();
                HttpServletRequest request = null;
                HttpServletResponse response = null;
                HttpSession session=null;
                for (int i = 0; i < args.length; i++) {
                    //获得参数中的 request && response
                    if (args[i] instanceof HttpServletRequest) {
                        request = (HttpServletRequest) args[i];
                    }
                    if (args[i] instanceof HttpServletResponse) {
                        response = (HttpServletResponse) args[i];
                    }
                }
                boolean needSaveSession = token.save();
                if (needSaveSession){
                    String uuid = UUID.randomUUID().toString();
                    request.getSession().setAttribute( "tokenliu" , uuid);

                    log.info("进入表单页面，Token值为："+uuid);
                }
                boolean needRemoveSession = token.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request,session)) {
                        log.info("表单重复提交");
                    /*    //输出到页面
                        response.setContentType("text/html; charset=UTF-8"); //转码
                        PrintWriter out=response.getWriter();
                        out.print("<script>");
                        out.print("alert('请不要重复提交!');");
                        //out.println("window.location.href='login.html'");
                        //out.println("history.back();");
                        out.print("</script>");
                        out.flush();
                        out.close();
                        //throw new FormRepeatException("表单重复提交");*/
                    }
                    request.getSession(false).removeAttribute( "tokenliu" );
                }
            }
        } catch (FormRepeatException e){
            throw e;
        } catch (Exception e){
            log.info("token 发生异常 : "+e);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request, HttpSession session) throws FormRepeatException {
        String serverToken = (String) request.getSession( false ).getAttribute( "tokenliu" );
        if (serverToken == null ) {
            System.out.println("---"+"serverToken"+serverToken);
            //throw new FormRepeatException("session 为空");
            return true;
        }
        String clinetToken = request.getParameter( "tokenliujiayi" );
        if (clinetToken == null || clinetToken.equals("")) {
            System.out.println("clinetToken"+clinetToken+"---"+"serverToken"+serverToken);
           // throw new FormRepeatException("请从正常页面进入！");
           return true;
        }
        if (!serverToken.equals(clinetToken)) {
            System.out.println("clinetToken"+clinetToken+"---"+"serverToken"+serverToken);
            //throw new FormRepeatException("重复表单提交！");
            return true ;
        }
        log.info("校验是否重复提交：表单页面Token值为："+clinetToken + ",Session中的Token值为:"+serverToken);
        return false ;
    }
}
