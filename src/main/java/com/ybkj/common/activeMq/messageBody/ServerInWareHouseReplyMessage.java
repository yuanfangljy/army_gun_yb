package com.ybkj.common.activeMq.messageBody;

public class ServerInWareHouseReplyMessage extends SimpleMessage {
	
	private ServerInWareHouseReplyBody serverInWareHouseReplyBody;

	public ServerInWareHouseReplyBody getServerInWareHouseReplyBody() {
		return serverInWareHouseReplyBody;
	}

	public void setServerInWareHouseReplyBody(ServerInWareHouseReplyBody serverInWareHouseReplyBody) {
		this.serverInWareHouseReplyBody = serverInWareHouseReplyBody;
	}

}
