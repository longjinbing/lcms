package com.ljb.security;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.security.config.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/25<br>
 * 描述: <br>
 */
@Configuration
@Profile("pro")
public class ProConfig {
    @Autowired
    private RedisCache securityRedisCache;
    @Bean
    public UserDetailsService userDetailsService() {
        Constructor<CachingUserDetailsService> ctor = null;
        try {
            ctor = CachingUserDetailsService.class.getDeclaredConstructor(UserDetailsService.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Assert.notNull(ctor, "CachingUserDetailsService constructor is null");
        ctor.setAccessible(true);
        SpringCacheBasedUserCache userCache = null;
        try {
            userCache = new SpringCacheBasedUserCache(securityRedisCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(ctor, "userCache is null");
        CachingUserDetailsService cachingUserDetailsService = BeanUtils.instantiateClass(ctor, new UserDetailsServiceImpl());
        cachingUserDetailsService.setUserCache(userCache);
        return cachingUserDetailsService;
    }
}
