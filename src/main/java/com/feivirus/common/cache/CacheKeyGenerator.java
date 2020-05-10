package com.feivirus.common.cache;

/**
 * @author feivirus
 * 所有的缓存的key统一管理
 */
public interface CacheKeyGenerator {
    /**
     * 缓存的key
     * @return
     */
    String stockKey();
}
