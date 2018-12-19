package com.ljb.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.util.Assert;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/12<br>
 * 描述: <br>
 */
public class DaoCache implements Cache {

    private static String id;
    private static RedisCacheManager cacheManager;

    public DaoCache(String id){
        Assert.notNull(id, "缓存名称为空");
        this.id=id;
    }

    public RedisCache getCache(){
        return (RedisCache) cacheManager.getCache(id);
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        getCache().put(key,value );
    }

    @Override
    public Object getObject(Object key) {
        org.springframework.cache.Cache.ValueWrapper valueWrapper=getCache().get(key);
        return valueWrapper==null?null:valueWrapper.get();
    }

    @Override
    public Object removeObject(Object key) {
        getCache().evict(key);
        return null;
    }

    @Override
    public void clear() {
        getCache().clear();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }


    public static void setCacheManager(RedisCacheManager cacheManager) {
        DaoCache.cacheManager = cacheManager;
    }
}
