package com.ybkj.common.activeMq;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

public class ConsumerConfig {
    @Bean
    DefaultJmsListenerContainerFactory JmsListenerContainerFactory()
	{
		 DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        factory.setSessionTransacted(true); //事务设置
	        factory.setConcurrency("3-10");//并发量接收
	        factory.setSessionAcknowledgeMode(1);
	        return factory;
	}
}
