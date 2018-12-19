package com.ljb.security;


import com.alibaba.fastjson.JSON;
import com.ljb.utils.R;
import com.ljb.utils.RequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/10<br>
 * 描述: <br>
 */
public class UnAuthorizationHandler implements AuthenticationFailureHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private String defaultFailureUrl="/account/unauhorize";
    private boolean forwardToDestination = false;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public UnAuthorizationHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(R.error(400,"权限不足")));
        } else {
            if (this.defaultFailureUrl == null) {
                this.logger.debug("No failure URL set, sending 401 Unauthorized error");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            } else {
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

}
