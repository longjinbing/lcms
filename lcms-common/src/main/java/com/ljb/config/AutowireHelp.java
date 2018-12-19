package com.ljb.config;

import com.ljb.cache.DaoCache;
import com.ljb.cache.RedisUtils;
import com.ljb.utils.EnvirmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/10<br>
 * 描述: <br>
 */
@Component
public class AutowireHelp {
    @Autowired
    public void setEnvirmentProperty(Environment env){
        EnvirmentUtils.setEnvironment(env);
    }
    @Autowired
    public void setRedisCacheManager(RedisCacheManager cacheManager) {
        DaoCache.setCacheManager(cacheManager);
    }

    @Autowired
    public void setJedisConnectionFactory(RedisTemplate redisTemplate) {
        RedisUtils.setRedisTemplate(redisTemplate);
    }
}
