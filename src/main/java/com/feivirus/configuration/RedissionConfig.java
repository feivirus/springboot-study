package com.feivirus.configuration;

import org.redisson.Redisson;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feivirus
 */
@Configuration
public class RedissionConfig {
    @Bean
    public Redisson redissionConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
