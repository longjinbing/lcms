package com.ljb.security;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/10<br>
 * 描述: <br>
 */
public class IdentityUtils {

    public static Long getUserId() {
        Identity identity = getIdentity();
        if(identity==null){
            return null;
        }
        return identity.getId();
    }

    public static Identity getIdentity() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            return null;
        }
        return (Identity) auth.getPrincipal();
    }

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null ? SecurityContextHolder.getContext().getAuthentication().isAuthenticated() : false;
    }
}
