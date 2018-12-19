package com.ljb.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/12<br>
 * 描述: <br>
 */
public class DefaultRedisCache extends RedisCache{
    private static RedisCacheConfiguration redisCacheConfiguration;

    public DefaultRedisCache(String name,RedisCacheWriter redisCacheWriter) {
        this(name, redisCacheWriter, redisCacheConfiguration);
    }

    public DefaultRedisCache(String name,RedisCacheConfiguration redisCacheConfiguration, RedisCacheWriter redisCacheWriter) {
        this(name, redisCacheWriter, redisCacheConfiguration);
    }

    protected DefaultRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }

    static {
        redisCacheConfiguration=RedisCacheConfiguration.defaultCacheConfig();
    }
}
