package com.ybkj.common.mqMessage;

public class ServerOutWareHouseMessage extends SimpleMessage{
	
	private ServerOutWareHouseBody serverOutWareHouseBody;

	public ServerOutWareHouseBody getServerOutWareHouseBody() {
		return serverOutWareHouseBody;
	}

	public void setServerOutWareHouseBody(ServerOutWareHouseBody serverOutWareHouseBody) {
		this.serverOutWareHouseBody = serverOutWareHouseBody;
	}
	
	
}
