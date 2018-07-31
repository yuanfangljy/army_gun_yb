package com.ybkj.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Description:  功能描述（出入库mq切面方法）
 *@Author:       刘家义
 *@CreateDate:   2018/7/31 9:19
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/31 9:19
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Aspect
@Component
@Slf4j
@Transactional
public class MessageAspect {

    @Pointcut("@annotation(com.ybkj.common.annotation.MessageValidate)")
    public void cut() {
    }

    public void doMessage(){

    }
}
