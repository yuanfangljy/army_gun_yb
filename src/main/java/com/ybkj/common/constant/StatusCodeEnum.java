package com.ybkj.common.constant;

import org.aspectj.weaver.ast.Not;

/**
 *@Description:  功能描述（记录操作状态码）
 *@Author:       刘家义
 *@CreateDate:   2018/7/25 14:25
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/25 14:25
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@SuppressWarnings("all")
public enum  StatusCodeEnum {
    //操作成功的标识
    SUCCESS(1),
    //字段校验失败的标识
    FIELD_FAIL(2),
    //sign校验失败字符
    SIGN_FAIL(3),
    //验证码超时标识
    SMS_CODE_TIMEOUT(4),
    //验证码错误标识
    SMS_CODE_FAIL(5),
    //登录失败
    LOGIN_FAIL(6),
    //锁定
    LOCKED(7),
    //异常状态
    EXCEPTION(8),
    //忽略
    IGNORE(10),
    //未登录
    UNLOGIN(0),
    //重复提交
    RESUBMIT(300),
    //没有选择
    NONULL(11),
    //支付密码
    PAYUPLOADING(12),
    //余额不足
    NotSUFFICIENTFUNDS(13),
    //不成功
    Fail(200),
    //运行时异常
    InternalServerErrorFail(500),
    //找不到页面
    NotFoundFail(404),
    //方法不被允许
    MethodNotAllowedFail(405),
    //服务器未能理解请求
    BadRequestFail(400),
    //由于媒介类型不被支持，服务器不会接受请求
    UnsupportedMediaTypeFail(415),
    ;
    private final Integer statusCode;

    private StatusCodeEnum(Integer statusCode){
        this.statusCode=statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
