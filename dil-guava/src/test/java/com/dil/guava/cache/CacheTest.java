package com.dil.guava.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * https://github.com/google/guava/wiki/
 */
@Slf4j
public class CacheTest {

    @Test
    public void test_cache() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumWeight(1000)
                .weigher((Weigher<String, String>) (x, y) -> x.length() - y.length())
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .recordStats()
                .build();

        cache.put("key", "val");
        log.info("测试结果：{}", cache.getIfPresent("key"));

        cache.invalidate("key");

        log.info("测试结果：{}", cache.getIfPresent("key"));
        log.info("测试结果：{}", cache.stats());
    }

}
