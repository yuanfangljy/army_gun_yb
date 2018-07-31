package com.ybkj.common.mqMessage;

import lombok.Data;

@Data
public class ServerOutWareHouseBody{
	private String reserve;
	private String bluetoothMac;
	private String gunTag;
	private String applyTime;
	private String deadlineTime;
	private String powerAlarmLevel;
	private String transmittingPower;
	private String broadcastInterval;
	private String conncetionInterval;
	private String connectionTimeout;
	private String softwareversiion;
	private String heartbeat;
	private String powerSampling;
	private String systemTime;
	private String matchTime;
	private String safeCode;
	private String deviceNo;

	
	
}
