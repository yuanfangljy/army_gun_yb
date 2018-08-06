package com.ybkj.common.activeMq.messageBody;

public class ServerOffLocationWarningStartStopMessage extends SimpleMessage {
	
	private ServerOffLocationWarningStartStopBody messageBody;

	public ServerOffLocationWarningStartStopBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ServerOffLocationWarningStartStopBody messageBody) {
		this.messageBody = messageBody;
	}


	
	
}
