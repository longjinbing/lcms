package com.ljb.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/21<br>
 * 描述: <br>
 */
public class ActionFilter extends GenericFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private AuthenticationFailureHandler failureHandler;
    private String staticPrefix="/static";
    private List<String> allActions;

    public ActionFilter(){
    }

    public ActionFilter(List<String> actions){
        this.allActions=actions;
        if(this.allActions==null){
            this.allActions=new ArrayList<>();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String path=request.getServletPath();
        if(path.startsWith(staticPrefix)){
            chain.doFilter(request,response );
        }else{
            if(!path.equals("/")) {
                int i = path.lastIndexOf("/");
                Boolean flag = isNumber(path.substring(i + 1, path.length()));
                path = flag ? path.substring(0, i+1) : path;
            }
            if(!allActions.contains(path) ||IdentityUtils.getIdentity().hasAction(path)){
                chain.doFilter(request,response );
            }else {
                this.unsuccessfulAuthentication(request, response, null);
            }
        }

    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler=new UnAuthorizationHandler();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
