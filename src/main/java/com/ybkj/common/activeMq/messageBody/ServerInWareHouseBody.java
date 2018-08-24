package com.ybkj.common.activeMq.messageBody;

import lombok.Data;

@Data
public class ServerInWareHouseBody {
	
	private String bluetoothMac;
	private String authCode;
	//警员编号
	private String deviceNo;
	//当前登录用户名
	private String userName;
	

	
}
