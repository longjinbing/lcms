package com.ljb.security;


import com.alibaba.fastjson.JSON;
import com.ljb.exception.DefaultKaptchaException;
import com.ljb.exception.KaptchaIncorrectException;
import com.ljb.exception.KaptchaNotFoundException;
import com.ljb.exception.KaptchaTimeoutException;
import com.ljb.utils.R;
import com.ljb.utils.RequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/10<br>
 * 描述: <br>
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private String defaultFailureUrl;
    private boolean forwardToDestination = false;
    private boolean allowSessionCreation = true;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomAuthenticationFailureHandler() {
    }

    public CustomAuthenticationFailureHandler(String defaultFailureUrl) {
        this.setDefaultFailureUrl(defaultFailureUrl);
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            String message=null;
            if(exception instanceof UsernameNotFoundException||exception instanceof BadCredentialsException){
                message="用户名或密码错误";
            }else if(exception instanceof LockedException || exception instanceof AccountExpiredException){
                message="账户异常,无法登入";
            }
            if(exception instanceof DefaultKaptchaException){
                DefaultKaptchaException defaultKaptchaException=(DefaultKaptchaException)exception;
                if(defaultKaptchaException.getKaptchaException() instanceof KaptchaNotFoundException)
                message="请输入验证码";
                if(defaultKaptchaException.getKaptchaException() instanceof KaptchaIncorrectException)
                    message="验证码错误";
                if(defaultKaptchaException.getKaptchaException() instanceof KaptchaTimeoutException)
                    message="验证码已过期，请重新输入";
            }
            writer.write(JSON.toJSONString(R.error(400,message)));
        } else {
            if (this.defaultFailureUrl == null) {
                this.logger.debug("No failure URL set, sending 401 Unauthorized error");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            } else {
                this.saveException(request, exception);
                if (this.forwardToDestination) {
                    this.logger.debug("Forwarding to " + this.defaultFailureUrl);
                    request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
                } else {
                    this.logger.debug("Redirecting to " + this.defaultFailureUrl);
                    this.redirectStrategy.sendRedirect(request, response, this.defaultFailureUrl);
                }
            }
        }

    }

    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
        if (this.forwardToDestination) {
            request.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
        } else {
            HttpSession session = request.getSession(false);
            if (session != null || this.allowSessionCreation) {
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
            }
        }

    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), () -> {
            return "'" + defaultFailureUrl + "' is not a valid redirect URL";
        });
        this.defaultFailureUrl = defaultFailureUrl;
    }

    protected boolean isUseForward() {
        return this.forwardToDestination;
    }

    public void setUseForward(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return this.redirectStrategy;
    }

    protected boolean isAllowSessionCreation() {
        return this.allowSessionCreation;
    }

    public void setAllowSessionCreation(boolean allowSessionCreation) {
        this.allowSessionCreation = allowSessionCreation;
    }
}
