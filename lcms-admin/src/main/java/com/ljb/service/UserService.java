package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.User;

/**
 * 系统用户管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface UserService extends BaseService<User,Long> {
    User findByCode(String code);
}
