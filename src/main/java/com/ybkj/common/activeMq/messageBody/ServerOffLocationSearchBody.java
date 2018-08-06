package com.ybkj.common.activeMq.messageBody;

import lombok.Data;

@Data
public class ServerOffLocationSearchBody {
	
	private String assDeviceNo; //协助警员编号
	private String bluetoothMac;//蓝牙mac
	private String lo;//经度
	private String la;//纬度
	private String lostDeviceNo;//丢失警员编号
	private String lostphone;//丢失电话
	private String lostTime;//丢失时间
		
}
