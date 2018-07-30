/*
package com.ybkj.common.activeMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;


@Slf4j
@Component
@EnableScheduling
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    //@Scheduled(fixedDelay=5000)
    public void send(){
        String reuslt = "测试消息队列：" + System.currentTimeMillis();
        log.info("发送信息："+reuslt);
        jmsMessagingTemplate.convertAndSend(queue,reuslt);
    }
}
*/
