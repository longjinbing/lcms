package com.ljb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/25<br>
 * 描述: <br>
 */
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
}
