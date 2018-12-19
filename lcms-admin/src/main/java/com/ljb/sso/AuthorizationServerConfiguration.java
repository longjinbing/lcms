package com.ljb.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/16<br>
 * 描述: <br>
 */
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "*";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private DataSource dataSource;
    WhitelabelApprovalEndpoint a;
    AuthorizationEndpoint b;
    // 初始化JdbcTokenStore
    @Autowired
    public TokenStore getTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    // 自定义数据库存储tokenStore
    @Autowired
    public TokenStore getMyTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Autowired
    private TokenStore getRedisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean   // 声明ApplyClientDetailService
    public ApplyClientDetailService getClientDetails() {
        return new ApplyClientDetailService();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端, 用于client认证
        clients.inMemory().withClient("platform")
                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .encode("123456"))
                .scopes("test").redirectUris("http://www.baidu.com").authorizedGrantTypes("authorization_code", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager).pathMapping("/oauth/confirm_access", "/oauth/confirm");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        /*//允许表单认证
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");*/
    }

}
