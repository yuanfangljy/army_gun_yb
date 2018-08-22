package com.ybkj.common.activeMq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.*;

@Slf4j
public class MQProducer {

    //"tcp://120.76.156.120:6160";   "tcp://120.76.156.120:6160";
    private static final String USERNAME = "admin"; //用户名
    private static final String PASSWORD = "admin";  //密码
    private static final String BROKENURL = "tcp://127.0.0.1:61616";
    private static final String Q_NAME = "WebInQueue";  //消息队列
    
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
                Map map1 = new HashMap();
                map.put("serviceType","BTOFFPOSITIONALARM");
                map.put("formatVersion","1.0");
                map.put("deviceType","1");
                map.put("serialNumber","201807222222221222");
                map.put("messageType","08");
                map.put("sendTime","20180722222222");
                map.put("sessionToken","sfdsfwet347284129");
                map1.put("deviceNo", "22222");
                map1.put("gunTag","11111");
                map1.put("state", "1");
                map.put("messageBody",map1);
                JSONObject json = (JSONObject) JSONObject.toJSON(map);
                log.info("----"+json.toJSONString());
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