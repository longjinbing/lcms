package com.ljb.security;

import com.ljb.utils.R;
import com.ljb.utils.RequestUtils;
import com.ljb.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/10<br>
 * 描述: <br>
 */
public class CustomUrlAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {
    public CustomUrlAuthenticationSuccessHandler() {
    }

    public CustomUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        this.setDefaultTargetUrl(defaultTargetUrl);
        setTargetUrlParameter("target");
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            String targetUrl = this.determineTargetUrl(request, response);
            targetUrl=RequestUtils.calculateRedirectUrl(request, targetUrl);
            UserInfo userInfo=(UserInfo) authentication.getPrincipal();
            Map<String,Object> objectMap=new HashMap<>();
            objectMap.put("username", userInfo.getUsername());
            R r=R.ok("登录成功").put("target",targetUrl).put("userinfo", objectMap);
            ResponseUtils.ajaxResponse(response, r);
        } else {
            this.handle(request, response, authentication);
        }
        this.clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }
    }
}
