package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiUser;
import com.ljb.model.AppRegisterModel;
import com.ljb.model.LoginStatus;
import com.ljb.model.SetPasswordModel;

/**
 * 商城用户Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiUserService extends BaseService<ApiUser, Long> {
    LoginStatus usernameAndPasswordLogin(String username, String password);

    LoginStatus  phoneLogin(String phone, String kaptcha);

    ApiUser register(AppRegisterModel apiUser);

    ApiUser updatePassword(SetPasswordModel setPasswordModel);

    ApiUser updateInfo(ApiUser apiUser);
}
