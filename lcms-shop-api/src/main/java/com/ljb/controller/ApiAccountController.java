package com.ljb.controller;

import com.ljb.entity.ApiUser;
import com.ljb.model.*;
import com.ljb.service.ApiUserService;
import com.ljb.utils.BeanUtils;
import com.ljb.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
@Api(value = "账户模块")
@RestController
@RequestMapping("api/account")
public class ApiAccountController {

    @Autowired
    private ApiUserService apiUserService;

    @ApiOperation(value = "用户登录", notes = "通过用户名密码登录")
    @PostMapping("/login")
    public R usernameAndPasswordLogin(@RequestBody UsernmaeAndPasswordModel loginModel) {
        LoginStatus loginStatus = apiUserService.usernameAndPasswordLogin(loginModel.getUsername(), loginModel.getPassword());
        if (!loginStatus.getSuccess()) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), loginStatus.getMessgae());
        }
        return R.ok("登录成功").put(loginStatus.getApiUser().getId());
    }

    @ApiOperation(value = "用户登录", notes = "通过手机号登录")
    @PostMapping("/login/phone")
    public R phoneLogin(@RequestBody PhoneModel loginModel) {
        LoginStatus loginStatus = apiUserService.phoneLogin(loginModel.getPhone(), loginModel.getKaptcha());
        if (!loginStatus.getSuccess()) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), loginStatus.getMessgae());
        }
        return R.ok().put(loginStatus.getApiUser().getId());
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R register(@RequestBody AppRegisterModel apiUser) {
        ApiUser apiUser1 = apiUserService.register(apiUser);
        if (apiUser1 == null) {
            return R.error(500, "未知错误");
        }
        return R.ok("注册成功").put(BeanUtils.filteBean(apiUserService.register(apiUser)));
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/password/update")
    public R updatePassword(@RequestBody SetPasswordModel setPasswordModel) {
        return R.ok().put(apiUserService.updatePassword(setPasswordModel));
    }
/*
    @ApiOperation(value = "重置密码")
    @PostMapping("/password/reset")
    public R resetPassword(@RequestBody  APi) {
        return R.ok().put( apiUserService.updateInfo(setPasswordModel));
    }*/

    public class LoginModel {
        private String username;
        private String password;
        private String phone;
        private String kaptcha;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getKaptcha() {
            return kaptcha;
        }

        public void setKaptcha(String kaptcha) {
            this.kaptcha = kaptcha;
        }
    }
}
