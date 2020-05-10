package com.feivirus.common.cache;

/**
 * @author feivirus
 * 屏蔽底层不同的缓存中间件,提供统一接口查询
 * 修改接口通过子接口Cache接口声明
 */
public interface CacheReader {
    Object get(String key);
}
