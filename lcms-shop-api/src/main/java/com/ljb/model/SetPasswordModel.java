package com.ljb.model;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
public class SetPasswordModel {
    private String newPassword;
    private String oldPassword;


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
