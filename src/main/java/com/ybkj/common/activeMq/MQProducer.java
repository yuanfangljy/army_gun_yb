package com.ybkj.common.activeMq;

import net.sf.json.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.*;

public class MQProducer {

    private static final String USERNAME = "admin"; //用户名
    private static final String PASSWORD = "admin";  //密码
    private static final String BROKENURL = "tcp://127.0.0.1:61616";
    private static final String Q_NAME = "liujiayi";  //消息队列
    
    public static void main(String[] args) {
            ConnectionFactory connectionFactory;
            Connection connection;
            Session session;
            Destination destination;
            MessageProducer producer;
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKENURL);
            try {
                connection = connectionFactory.createConnection();
                connection.start();
                session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
                destination = session.createQueue(Q_NAME);
                producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                Map map = new HashMap();
                map.put("deviceNo", "22222");
                map.put("gunTag","11111");
                map.put("state", "1");
                JSONObject json = JSONObject.fromObject(map);
                for(int i=0;i<1;i++){
                    //发送消息
                    producer.send(session.createTextMessage(json.toString()));
                }
                producer.close(); 
                System.out.println("消息发送完毕");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}