package com.ljb.security;

import com.ljb.utils.PasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("%%%%%%%%%-----查数据库"+authentication);
        return new UsernamePasswordAuthenticationToken("longjinbin", PasswordEncoderUtils.encode("123456"));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println("%%%%%%%%%-----+supports");
        return true;
    }
}
