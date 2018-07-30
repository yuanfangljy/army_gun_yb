package com.ybkj.common.mqMessage;

public class ServerOutWareHouseReplyMessage extends SimpleMessage{
	
	private ServerOutWareHouseReplyBody serverOutWareHouseReplyBody;

	public ServerOutWareHouseReplyBody getServerOutWareHouseReplyBody() {
		return serverOutWareHouseReplyBody;
	}

	public void setServerOutWareHouseReplyBody(ServerOutWareHouseReplyBody serverOutWareHouseReplyBody) {
		this.serverOutWareHouseReplyBody = serverOutWareHouseReplyBody;
	}
	
	
}
