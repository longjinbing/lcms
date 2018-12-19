package com.ljb.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/23<br>
 * 描述: <br>
 */
@Component
@ConfigurationProperties(prefix = "spring.profiles")
public class SpringProperties{
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
