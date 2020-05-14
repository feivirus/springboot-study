package com.feivirus.configuration.cache.reader;

import com.feivirus.common.cache.Cache;
import com.feivirus.common.cache.impl.MemcachedImpl;
import com.feivirus.common.cache.impl.RedisCacheImpl;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import net.spy.memcached.transcoders.SerializingTranscoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    private static final int COMPRESSION_THRESHOLD = 1024;
    private static final long OP_TIMEOUT = 1000L;
    private static final int TIMEOUT_EXCEPTION_THRESHOLD = 1998;

    @Bean(name = "cache")
    public Cache cache(MemcachedClient memcachedClient) {
        return new MemcachedImpl(memcachedClient);
    }

    @Bean(name = "redisCache")
    public Cache redisCache() {
        return new RedisCacheImpl();
    }

    @Bean
    private MemcachedClient memcachedClient() {
        MemcachedClientFactoryBean factoryBean = new MemcachedClientFactoryBean();

        factoryBean.setServers("127.0.0.1");
        factoryBean.setProtocol(ConnectionFactoryBuilder.Protocol.BINARY);

        SerializingTranscoder serializingTranscoder = new SerializingTranscoder();
        serializingTranscoder.setCompressionThreshold(COMPRESSION_THRESHOLD);
        factoryBean.setTranscoder(serializingTranscoder);

        factoryBean.setOpTimeout(OP_TIMEOUT);
        factoryBean.setTimeoutExceptionThreshold(TIMEOUT_EXCEPTION_THRESHOLD);
        factoryBean.setLocatorType(ConnectionFactoryBuilder.Locator.CONSISTENT);
        factoryBean.setFailureMode(FailureMode.Redistribute);
        factoryBean.setUseNagleAlgorithm(false);

        try {
            //factoryBean.afterPropertiesSet();
            MemcachedClient memcachedClient = (MemcachedClient)factoryBean.getObject();
            return memcachedClient;
        } catch (Exception ex) {

        }
        return null;
    }
}
