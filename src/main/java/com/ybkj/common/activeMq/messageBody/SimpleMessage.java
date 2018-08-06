package com.ybkj.common.activeMq.messageBody;

import lombok.Data;

/**
 *@Description:  功能描述（出库消息报文：无消息体）
 *@Author:       刘家义
 *@CreateDate:   2018/7/31 9:53
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/7/31 9:53
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@Data
public class SimpleMessage {
	
	private String serviceType;
	private String formatVersion;
	private Integer deviceType;
	private String serialNumber;
	private String messageType;
	private String sendTime;
	private String sessionToken;
}
