package com.ybkj.common.activeMq;


import com.alibaba.fastjson.JSONObject;
import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.service.impl.GunServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 消费者
 */
@SuppressWarnings("all")
@Slf4j
@Component
public class Consumer {

    @Autowired
    GunServiceImpl gunService;
    String  consumer="${storageQueue}";
   /* *//**
     * 枪支射弹基数查询
     * @return
     * @throws Exception
     *//*
    @JmsListener(destination = "${storageQueue}")
    public BaseModel receiveBulletNumberApply(String msg) throws Exception{
        JSONObject json = JSONObject.parseObject(msg);
        if(json.getString("messageType").equals("") && json.getString("messageType").equals("27")){
            //查询数据库
        }

        log.info("监听器收到msg:" + json);

        return null;
    }*/


    /**
     * 手动消费
     * @return
     * @throws JMSException
     */
    public BaseModel manualBulletNumberApply() throws JMSException {
        BaseModel baseModel=new BaseModel();
        try {
            //获取mq连接工程
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,"tcp://127.0.0.1:61616");
            //创建连接
            Connection connection=connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建会话工厂
            Session session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //创建队列
            Destination destination=session.createQueue(consumer);
            MessageConsumer consumer = session.createConsumer(destination);
            while (true){
                //监听消息
                TextMessage receive = (TextMessage)consumer.receive();
                if (receive!=null){
                    String text = receive.getText();
                    JSONObject json = JSONObject.parseObject(text);
                    baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
                    baseModel.setErrorMessage(json.toJSONString());
                    log.info("-------射弹基数消费者获取到的信息------"+json);
                    session.commit();
                    //手动消费：会话工厂的第二个参数要是，Session.CLIENT_ACKNOWLEDGE
                    // receive.acknowledge();
                }else{
                    break;
                }
            }
        }catch (Exception e){
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            e.printStackTrace();
        }
        return baseModel;
    }

}
