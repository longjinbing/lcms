package com.ljb.security;

import com.ljb.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/15<br>
 * 描述: <br>
 */
public class RedisSecurityContextRepository implements SecurityContextRepository {
    private final static String SESSION_PARAMETER= Constant.SESSION_PARAMETER;
    private String cookieKey;
    @Autowired
    private RedisCache securityRedisCache;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        SecurityContext securityContext=null;
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName()==SESSION_PARAMETER){
                this.cookieKey=!cookie.getValue().isEmpty()?cookie.getValue():null;
                if(!this.cookieKey.isEmpty())
                securityContext= (SecurityContext) securityRedisCache.get(this.cookieKey);
            }
        }
        if(securityContext==null){
          securityContext=this.createSecurityContext();
        }
        return securityContext;
    }

    @Override
    public void saveContext(SecurityContext securityContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContext saveedSecurityContext= (SecurityContext) securityRedisCache.get(this.cookieKey);
        if(saveedSecurityContext!=null){
            securityRedisCache.put(this.cookieKey, securityContext);
        }else{
            securityRedisCache.put("", securityContext);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest httpServletRequest) {
        return false;
    }

    public SecurityContext createSecurityContext(){
      return  SecurityContextHolder.createEmptyContext();
    }
}
