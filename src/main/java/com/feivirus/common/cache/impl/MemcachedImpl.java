package com.feivirus.common.cache.impl;

import com.feivirus.common.cache.Cache;
import net.spy.memcached.MemcachedClient;

/**
 * @author feivirus
 */
public class MemcachedImpl implements Cache {

    private MemcachedClient memcachedClient = null;

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
        return null;
    }
}
