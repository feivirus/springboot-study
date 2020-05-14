package com.feivirus.configuration.cache.reader;

import com.feivirus.common.cache.Cache;
import com.feivirus.common.cache.impl.StockReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheReaderConfiguration {
    @Autowired
    private Cache cache;

    @Bean
    private StockReader stockReader() {
        return new StockReader(cache);
    }
}
