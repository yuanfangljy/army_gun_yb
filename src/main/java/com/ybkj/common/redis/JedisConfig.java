/*
package com.ybkj.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

*/
/**
 * Jedis配置文件
 *
 * @author karyzeng 2018.03.06
 * @version 1.0
 *//*

@Configuration
@EnableCaching
public class JedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.max.timeout}")
    private int timeout;

    @Value("${redis.max.idle}")
    private int maxIdle;

    @Value("${redis.max.waitmillis}")
    private long maxWaitMillis;

    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }

}*/
