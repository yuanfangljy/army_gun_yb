package com.ybkj.common.mqMessage;

public class ServerInWareHouseMessage extends SimpleMessage {
	
	private ServerInWareHouseBody serverInWareHouseBody;

	public ServerInWareHouseBody getServerInWareHouseBody() {
		return serverInWareHouseBody;
	}

	public void setServerInWareHouseBody(ServerInWareHouseBody serverInWareHouseBody) {
		this.serverInWareHouseBody = serverInWareHouseBody;
	}
	
	
}
