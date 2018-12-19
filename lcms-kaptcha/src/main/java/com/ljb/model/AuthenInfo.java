package com.ljb.model;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/26<br>
 * 描述: <br>
 */
public class AuthenInfo {
    private String username;
    private String password;
    private String kaptcha;
    private Boolean remember;
    private String kaptchaKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean isRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }


    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public String getKaptchaKey() {
        return kaptchaKey;
    }

    public void setKaptchaKey(String kaptchaKey) {
        this.kaptchaKey = kaptchaKey;
    }
}
