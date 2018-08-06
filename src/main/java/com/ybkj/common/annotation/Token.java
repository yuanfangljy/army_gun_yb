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
@Retention(RetentionPolicy.RUNTIME) // 在运行时可以获取
@Target(value = {ElementType.METHOD, ElementType.TYPE})  // 作用到类，方法，接口上等
public @interface Token {
    //生成 Token 标志
    boolean save() default false ;
    //移除 Token 值
    boolean remove() default false ;
}
