package com.ybkj.common.activeMq.messageBody;

public class ServerOutWareHouseMessage extends SimpleMessage {
	
	private ServerOutWareHouseBody serverOutWareHouseBody;

	public ServerOutWareHouseBody getServerOutWareHouseBody() {
		return serverOutWareHouseBody;
	}

	public void setServerOutWareHouseBody(ServerOutWareHouseBody serverOutWareHouseBody) {
		this.serverOutWareHouseBody = serverOutWareHouseBody;
	}
	
	
}
