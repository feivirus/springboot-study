package com.feivirus.common.cache.impl;

import com.alibaba.fastjson.JSONObject;
import com.feivirus.common.cache.CacheKeyGenerator;
import com.feivirus.common.cache.CacheReader;
import com.feivirus.common.cache.impl.Reader;

/**
 * @author feivirus
 */
public class StockReader extends Reader {
    public StockReader(CacheReader cacheReader) {
        super(cacheReader);
    }

    public StockReader(CacheReader cacheReader, CacheKeyGenerator cacheKeyGenerator) {
        super(cacheReader, cacheKeyGenerator);
    }

    Integer getStock() {
        Object o = cacheReader.get(cacheKeyGenerator.stockKey());
        if (o != null) {
            return JSONObject.parseObject((String)o, Integer.class);
        }
        return null;
    }
}
