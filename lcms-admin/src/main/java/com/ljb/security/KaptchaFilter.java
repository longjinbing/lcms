package com.ljb.security;

import com.alibaba.fastjson.JSONObject;
import com.ljb.exception.DefaultKaptchaException;
import com.ljb.exception.KaptchaIncorrectException;
import com.ljb.exception.KaptchaNotFoundException;
import com.ljb.exception.KaptchaTimeoutException;
import com.ljb.model.AuthenInfo;
import com.ljb.service.Kaptcha;
import com.ljb.utils.Constant;
import com.ljb.utils.EnvirmentUtils;
import com.ljb.utils.RequestUtils;
import com.ljb.utils.RequestWrapperUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/21<br>
 * 描述: <br>
 */
public class KaptchaFilter extends GenericFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private AuthenticationFailureHandler failureHandler;
    private RequestMatcher requiresAuthenticationRequestMatcher=new AntPathRequestMatcher(Constant.LOGIN_URL, "POST");
    private Kaptcha kaptcha;


    public KaptchaFilter(Kaptcha kaptcha){
        this.kaptcha=kaptcha;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
            if (!this.requiresAuthenticationRequestMatcher.matches(request)) {
                chain.doFilter(request, response);
            } else {
                try {
                    JSONObject jsObject = RequestUtils.getRequestJsonObject(request);
                    AuthenInfo authenInfo = jsObject.toJavaObject(AuthenInfo.class);
                    String code = authenInfo.getKaptcha();
                    if ( !EnvirmentUtils.isDev() && (code.isEmpty() || authenInfo.getKaptchaKey() == null || !kaptcha.validate(code, authenInfo.getKaptchaKey()))) {
                        this.unsuccessfulAuthentication(request, response, new DefaultKaptchaException(new KaptchaNotFoundException()));
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("username", authenInfo.getUsername());
                        map.put("password", authenInfo.getPassword());
                        map.put("remember", authenInfo.isRemember() == null ? false : authenInfo.isRemember());
                        RequestWrapperUtils requestWrapperUtils = new RequestWrapperUtils(request, map);
                        chain.doFilter(requestWrapperUtils, response);
                    }
                } catch (NullPointerException ne) {
                    this.unsuccessfulAuthentication(request, response, new DefaultKaptchaException(new KaptchaNotFoundException()));
                } catch (KaptchaNotFoundException kne) {
                    this.unsuccessfulAuthentication(request, response, new DefaultKaptchaException(kne));
                } catch (KaptchaIncorrectException kie) {
                    this.unsuccessfulAuthentication(request, response, new DefaultKaptchaException(kie));
                } catch (KaptchaTimeoutException kte) {
                    this.unsuccessfulAuthentication(request, response, new DefaultKaptchaException(kte));
                }
            }
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler=new CustomAuthenticationFailureHandler();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
