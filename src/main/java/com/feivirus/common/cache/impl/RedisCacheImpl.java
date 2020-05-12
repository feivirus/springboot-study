package com.feivirus.common.cache.impl;

import com.feivirus.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author feivirus
 */
public class RedisCacheImpl implements Cache {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean set(String key, Object value, int expiry) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean add(String key, Object value, int expiry) {
        return false;
    }

    @Override
    public long incr(String key, int by) {
        return 0;
    }

    @Override
    public long incr(String key, int by, int def) {
        return 0;
    }

    @Override
    public long incr(String key, int by, int def, int expiry) {
        return 0;
    }

    @Override
    public Object get(String key) {
        Jedis jedis = jedisPool.getResource();

        if (jedis == null) {
            return null;
        }
        String result = jedis.get(key);
        jedis.close();
        return result;
    }
}
