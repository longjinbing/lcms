package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.UserRole;
import com.ljb.model.UserRoleModel;

/**
 * 用户角色管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface UserRoleService extends BaseService<UserRole,Long> {
    int saveUserRole(UserRoleModel userRoleModel);
}
