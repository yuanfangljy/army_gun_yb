package com.ybkj.common.activeMq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TopicProducer {


    public static void main(String[] args) throws JMSException {
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessions = request.getSession();
*/
        //获取mq连接工程
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://127.0.0.1:61616");
        //创建连接
        Connection connection=connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话工厂
        Session session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        //创建主题
       // Destination destination=session.createQueue("yuanfang");
        MessageProducer producer=session.createProducer(null);
        //不持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

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
        map1.put("userName", "wt");
        map.put("messageBody",map1);
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        log.info("----"+json.toJSONString());
        for(int i=0;i<1;i++){
            //发送消息
            sendMsg(session,producer,json.toJSONString());
        }
    }

    static  public  void  sendMsg(Session session,MessageProducer producer,String i) throws JMSException {
        TextMessage textMessage=session.createTextMessage(i);
        Topic wuqi = session.createTopic("wuqi");
        producer.send(wuqi,textMessage);
    }

}
