package com.feivirus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author feivirus
 */
@Configuration
public class JedisConfig {

    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool("qa.vm.com", 6379);
        return jedisPool;
    }
}
