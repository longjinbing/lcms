package com.ljb.model;

import com.ljb.entity.ApiUser;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
public class LoginStatus {
    private Boolean success;
    private String messgae;
    private ApiUser apiUser;

    public static LoginStatus error(String message){
        LoginStatus loginStatus=new LoginStatus();
        loginStatus.setSuccess(false);
        loginStatus.setMessgae(message);
        return loginStatus;
    }

    public static LoginStatus usernameOrPasswordError(){
        LoginStatus loginStatus=new LoginStatus();
        loginStatus.setSuccess(false);
        loginStatus.setMessgae("用户名或密码错误");
        return loginStatus;
    }

    public static LoginStatus success(ApiUser apiUser){
        LoginStatus loginStatus=new LoginStatus();
        loginStatus.setSuccess(true);
        loginStatus.setApiUser(apiUser);
        return loginStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ApiUser getApiUser() {
        return apiUser;
    }

    public void setApiUser(ApiUser apiUser) {
        this.apiUser = apiUser;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
