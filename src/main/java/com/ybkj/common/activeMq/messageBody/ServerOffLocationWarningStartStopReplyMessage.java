package com.ybkj.common.activeMq.messageBody;

public class ServerOffLocationWarningStartStopReplyMessage extends SimpleMessage{
	private ServerOffLocationWarningStartStopReplyBody messageBody;

	public ServerOffLocationWarningStartStopReplyBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ServerOffLocationWarningStartStopReplyBody messageBody) {
		this.messageBody = messageBody;
	}

}
