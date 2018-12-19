package com.ljb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/15<br>
 * 描述: <br>
 */
public class RedisSessionAuthenticationStrategy implements SessionAuthenticationStrategy {
    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SessionAuthenticationException {

    }
}
