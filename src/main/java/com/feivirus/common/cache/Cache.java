package com.feivirus.common.cache;

/**
 * @author feivirus
 *
 * 屏蔽底层不同的缓存中间件,提供统一接口修改.
 * 查询是CacheReader接口
 */
public interface Cache extends CacheReader {

    public boolean set(String key, Object value, int expiry);

    public boolean delete(String key);

    public boolean add(String key, Object value, int expiry);

    public long incr(String key , int by);

    /**
     * 增加值
     * @param key
     * @param by
     * @param def 默认值
     * @return
     */
    public long incr(String key, int by, int def);

    public long incr(String key,int by, int def, int expiry);
}
