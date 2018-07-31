package com.ybkj.common.annotation;


import java.lang.annotation.*;

/**
 *@Description:  功能描述（用于出入库发送mq注解）
 *@Author:       刘家义
 *@CreateDate:   2018/7/31 9:16
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/31 9:16
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Documented
@Retention(value = RetentionPolicy.SOURCE)
@Target(value = ElementType.METHOD)
public @interface MessageValidate {
    String type() default "";
}
