package com.feivirus.redpacket.helper;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author feivirus
 */
@Component
public class RedisHelper {
    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();

        if (jedis == null) {
            return null;
        }
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    /**
     * 设置过期时间
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = jedisPool.getResource();
        jedis.expire(key, seconds);
    }

    /**
     * 左侧入队
     * @param key
     * @param value
     * @param second
     * @param <T>
     * @return
     */
    public <T> Long lpush(String key, T value, int second) {
        Jedis jedis = jedisPool.getResource();
        byte[] jsonBytes = JSONObject.toJSONString(value).getBytes();
        Long result = jedis.lpush(key.getBytes(), jsonBytes);
        jedis.expire(key, second);
        return result;
    }

    /**
     * 出队
     * @param key
     * @param waitSeconds
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T brpop(String key, int waitSeconds, Class<T> clazz) {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return null;
        }

        List<byte[]> values = jedis.brpop(waitSeconds, key.getBytes());
        if (values != null && values.size() > 0) {
            byte[] value = values.get(1);
            return JSONObject.parseObject(String.valueOf(value), clazz);
        }
        return null;
    }

    public long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            return 0;
        }
        long len = jedis.llen(key);
        return len;
    }
}
