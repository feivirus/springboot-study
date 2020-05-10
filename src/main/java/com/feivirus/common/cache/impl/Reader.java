package com.feivirus.common.cache.impl;

import com.feivirus.common.cache.CacheKeyGenerator;
import com.feivirus.common.cache.CacheReader;
import com.feivirus.common.cache.impl.CommonCacheKeyGenerator;

/**
 * @author feivirus
 */
public abstract class Reader {
    protected final CacheReader cacheReader;

    protected final CacheKeyGenerator cacheKeyGenerator;

    public Reader(CacheReader cacheReader) {
        this(cacheReader, new CommonCacheKeyGenerator());
    }

    public Reader(CacheReader cacheReader, CacheKeyGenerator cacheKeyGenerator) {
        this.cacheReader = cacheReader;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }
}
