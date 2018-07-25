package com.ybkj.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *@Description:  功能描述（基本Model，用于数据与浏览器进行交互）
 *@Author:       刘家义
 *@CreateDate:   2018/7/24 19:29
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/24 19:29
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Data
public class BaseModel implements Serializable {

	private int status;// 1：成功 2：字段校验失败  3:sign校验失败  4:token验证失败
	private String errorMessage;// 错误信息

	private Map<String, Object> mapResults = new HashMap<String,Object>(16);// 存放查询结果
	private String roleKey;// 用于存放编码
	private Map<String,Object> signValidate = new HashMap<String,Object>(16);//用于存放需要进行校验的信息
	private String token;//登录的状态令牌
	private long loginTimeOut;//是否登录超时
	private String sign;//存放校验码
	private String formToken;
	
	//用户装返回给浏览器的数据
		private Map<String ,Object> extend=new HashMap<String ,Object>();
		
		public BaseModel add(String key, Object value){
			this.getExtend().put(key, value);
			return this;
		}
		public Map<String, Object> getExtend() {
			return extend;
		}
		public void setExtend(Map<String, Object> extend) {
			this.extend = extend;
		}
	



}
