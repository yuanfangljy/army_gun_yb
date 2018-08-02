package com.ybkj.common.activeMq.messageBody;

public class ServerOutWareHouseReplyMessage extends SimpleMessage {
	
	private ServerOutWareHouseReplyBody serverOutWareHouseReplyBody;

	public ServerOutWareHouseReplyBody getServerOutWareHouseReplyBody() {
		return serverOutWareHouseReplyBody;
	}

	public void setServerOutWareHouseReplyBody(ServerOutWareHouseReplyBody serverOutWareHouseReplyBody) {
		this.serverOutWareHouseReplyBody = serverOutWareHouseReplyBody;
	}
	
	
}
