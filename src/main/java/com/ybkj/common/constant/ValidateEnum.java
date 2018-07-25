package com.ybkj.common.constant;
/**
 *@Description:  功能描述（ 用来记录正则表达式的校验规则 ）
 *@Author:       刘家义
 *@CreateDate:   2018/7/25 19:16
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/25 19:16
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
public enum ValidateEnum {
	MOBILE_RULE("1[3|4|5|7|8][0-9]{9}$"),
	SMSCODE_RULE("[1-9]\\d{5}$"),
	USERNAME_RULE("[a-zA-Z]{1}([a-zA-Z0-9]){4,19}$"),
	QQ_RULE("[1-9][0-9]{7,11}$"),
	WECHAT_RULE("(\\w){6,20}$"),
	PASSWORD_RULE("(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$"),
	//发布信息的内容
	PUBLISH_CONTENT_RULE("^.{5,90}$"),
	//支付密码6位数
	PAYPASSWORD_RULE("^\\d{6}$"),
	//充值余额是否是数字,验证数字
	FIGURE_RULE("^[0-9]*$ ");
	
	private final String rule;
	ValidateEnum(String rule){
		this.rule = rule;
	}
	
	public String getRule(){
		return rule;
	}
}
