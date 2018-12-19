/**
 * Copyright © 2018 TaoYu (tracy5546@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ljb.auto;


import com.ljb.producer.impl.DefaultKaptcha;
import com.ljb.service.Kaptcha;
import com.ljb.service.impl.GoogleKaptcha;
import com.ljb.service.impl.KaptchaImpl;
import com.ljb.utils.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * 谷歌验证码 的springBoot快速启动器
 *
 * @author TaoYu MaYing
 */
@Configuration
@ConditionalOnClass({DefaultKaptcha.class})
@ConfigurationProperties
@EnableConfigurationProperties({KaptchaProperties.class})
public class KaptchaAutoConfiguration {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    private final KaptchaProperties properties;

    public KaptchaAutoConfiguration(KaptchaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DefaultKaptcha defaultKaptcha() {
        Properties prop = new Properties();
        prop.setProperty("kaptcha.image.width", String.valueOf(this.properties.getWidth()));
        prop.setProperty("kaptcha.image.height", String.valueOf(this.properties.getHeight()));
        KaptchaProperties.Content content = this.properties.getContent();
        prop.setProperty("kaptcha.textproducer.char.string", content.getSource());
        prop.setProperty("kaptcha.textproducer.char.length", String.valueOf(content.getLength()));
        prop.setProperty("kaptcha.textproducer.char.space", String.valueOf(content.getSpace()));
        KaptchaProperties.BackgroundColor backgroundColor = this.properties.getBackgroundColor();
        prop.setProperty("kaptcha.background.clear.from", backgroundColor.getFrom());
        prop.setProperty("kaptcha.background.clear.to", backgroundColor.getTo());
        KaptchaProperties.Border border = this.properties.getBorder();
        prop.setProperty("kaptcha.border", border.getEnabled() ? "yes" : "no");
        prop.setProperty("kaptcha.border.color", border.getColor());
        prop.setProperty("kaptcha.border.thickness", String.valueOf(border.getThickness()));
        KaptchaProperties.Font font = this.properties.getFont();
        prop.setProperty("kaptcha.textproducer.font.names", font.getName());
        prop.setProperty("kaptcha.textproducer.font.size", String.valueOf(font.getSize()));
        prop.setProperty("kaptcha.textproducer.font.color", font.getColor());
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(prop));
        return defaultKaptcha;
}

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig(); //最大连接数
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxIdle(maxIdle);
        return jedisPoolConfig;
    }

    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcf.poolConfig(jedisPoolConfig());
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    @ConditionalOnClass(CacheManager.class)
    public Kaptcha kaptchaRender(DefaultKaptcha defaultKaptcha) {
        return new KaptchaImpl(defaultKaptcha,jedisConnectionFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public Kaptcha kaptcha(DefaultKaptcha defaultKaptcha) {
        return new GoogleKaptcha(defaultKaptcha);
    }
}
