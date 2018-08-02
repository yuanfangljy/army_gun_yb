package com.ybkj.common.activeMq.messageBody;

public class AuthCodeMessage extends SimpleMessage {

	private AuthCodeMessageBody messageBody;

	public AuthCodeMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(AuthCodeMessageBody messageBody) {
		this.messageBody = messageBody;
	}
}
