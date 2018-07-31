package com.ybkj.common.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
public class QueueConfig {
       @Value("${queue}")
       private String queue;
      /* @Value("${topic}")
       private String topic;*/

	@Bean
	public Queue logQueue() {
	return new ActiveMQQueue(queue);
	}
	@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory, Queue queue){
		JmsTemplate jmsTemplate=new JmsTemplate();
		//进行持久化 1否 2是
		jmsTemplate.setDeliveryMode(2);
		jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
		//此处默认是队列，也可以设置成主题
		jmsTemplate.setDefaultDestination(queue);
		//客户端签收模式，开启事务
		jmsTemplate.setSessionAcknowledgeMode(4);
		return jmsTemplate;
	}
	//定义一个消息监听连接工厂，这里定义的是点对点模式的监听器连接工厂
	@Bean(name = "jmsQueueListener")
	public DefaultJmsListenerContainerFactory defaultJcaListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
		DefaultJmsListenerContainerFactory factory=new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory);
		//设置连接数
		factory.setConcurrency("1-10");
		//重连间隔时间
		factory.setRecoveryInterval(1000L);
		factory.setSessionAcknowledgeMode(4);
		return factory;
	}
}
