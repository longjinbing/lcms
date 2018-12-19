package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 商城用户管理实体
 * 表名 shop_user
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@TableName("shop_user")
public class ShopUser extends BaseEntity{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String username;
            private String password;
            private Integer gender;
            private Date birthday;
            private Date registerTime;
            private Date lastLoginTime;
            private String lastLoginIp;
            private Integer userLevelId;
            private String nickname;
            private String mobile;
            private String registerIp;
            private String avatar;
            private String weixinOpenid;
            private Integer status;
    /**
     * 设置：
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取：
     */
    public Integer getGender() {
        return gender;
    }
    /**
     * 设置：
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：
     */
    public Date getBirthday() {
        return birthday;
    }
    /**
     * 设置：
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取：
     */
    public Date getRegisterTime() {
        return registerTime;
    }
    /**
     * 设置：
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取：
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    /**
     * 设置：
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取：
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }
    /**
     * 设置：
     */
    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    /**
     * 获取：
     */
    public Integer getUserLevelId() {
        return userLevelId;
    }
    /**
     * 设置：
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取：
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * 设置：
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    /**
     * 获取：
     */
    public String getRegisterIp() {
        return registerIp;
    }
    /**
     * 设置：
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 设置：
     */
    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    /**
     * 获取：
     */
    public String getWeixinOpenid() {
        return weixinOpenid;
    }
    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }
}
