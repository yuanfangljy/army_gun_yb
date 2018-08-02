package com.ybkj.common.activeMq.messageBody;

import lombok.Data;

/**
 *@Description:  功能描述（出库消息：报文体）
 *@Author:       刘家义
 *@CreateDate:   2018/7/31 9:53
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/31 9:53
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Data
public class AuthCodeMessageBody {

	//预留
	private String reserve;
	//枪支id
	private String bluetoothMac;
	//枪号
	private String gunTag;
	//领用时间
	private String applyTime;
	//归还截止时间
	private String deadlineTime;
	//电量报警级别
	private String powerAlarmLevel;
	//发射功率
	private String transmittingPower;
	//广播间隔
	private String broadcastInterval;
	//连接间隔
	private String connectionInterval;
	//连接超时
	private String connectionTimeout;
	//软硬件版本
	private String softwareversion;
	//心跳间隔
	private String heartbeat;
	//
	private String powerSampling;
	//系统时间
	private String systemTime;
	//匹配最大时间:随行设备匹配最大时间（绑定超时）
	private String matchTime;
	//安全字
	private String safeCode;
	//警员编号
	private String deviceNo;
}
