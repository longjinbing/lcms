package com.ljb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/9<br>
 * 描述: <br>
 */
@Component
public class UploadConfig {
    @Value("${spring.servlet.multipart.location}")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
