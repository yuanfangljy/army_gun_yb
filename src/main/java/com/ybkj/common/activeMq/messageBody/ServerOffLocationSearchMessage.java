package com.ybkj.common.activeMq.messageBody;

public class ServerOffLocationSearchMessage extends SimpleMessage {
	
	private ServerOffLocationSearchBody messageBody;

	public ServerOffLocationSearchBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ServerOffLocationSearchBody messageBody) {
		this.messageBody = messageBody;
	}


	
	
}
