package com.ybkj.common.activeMq.messageBody;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class QueueSender {

	public static void main(String[] args) throws JMSException, InterruptedException {
		ConnectionFactory connectionFactory =  new ActiveMQConnectionFactory("tcp://112.74.51.194:61616");
		Connection connection =  connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("InputQueue");
		
		MessageProducer producer =  session.createProducer(destination);
		for(int i=0;i<1;i++) {
			AuthCodeMessage authCodeMessageBody = new AuthCodeMessage();
			AuthCodeMessageBody authSunBody = new AuthCodeMessageBody();
			/*authSunBody.setCommand("234223");
			authSunBody.setLa("234234");
			authSunBody.setLo("234234");
			authSunBody.setUsername("123456");*/
			
			authCodeMessageBody.setDeviceType(1);
			authCodeMessageBody.setFormatVersion("1.0");
			authCodeMessageBody.setMessageBody(authSunBody);
			authCodeMessageBody.setMessageType("01");
			authCodeMessageBody.setSendTime("20180725121212");
			authCodeMessageBody.setSerialNumber("1234567894564621");
			authCodeMessageBody.setServiceType("aafafasfsaffsfsfsfs");
			
			System.out.println(JSONObject.toJSONString(authCodeMessageBody));
			
			TextMessage message =  session.createTextMessage(JSONObject.toJSONString(authCodeMessageBody));
			Thread.sleep(1000);
			
			producer.send(message);
		}
		session.commit();
		session.close();
		connection.close();
	}
}
