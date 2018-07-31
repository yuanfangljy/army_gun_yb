package com.ybkj.common.mqMessage;

public class AuthCodeMessage extends SimpleMessage{
	
	private AuthCodeMessageBody messageBody;

	public AuthCodeMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(AuthCodeMessageBody messageBody) {
		this.messageBody = messageBody;
	}
}
