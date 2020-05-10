package com.feivirus.common.cache.impl;

import com.feivirus.common.cache.CacheKeyGenerator;

/**
 * @author feivirus
 */
public class CommonCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public String stockKey() {
        return "stock";
    }
}
