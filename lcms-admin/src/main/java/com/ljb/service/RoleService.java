package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Role;
import com.ljb.model.RoleDeptModel;

import java.util.List;
import java.util.Map;

/**
 * 角色管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface RoleService extends BaseService<Role,Long> {
    Role update(RoleDeptModel model);
    Role save(RoleDeptModel model);
    List<Map<String, Object>> roleListByuserIdAndAdminId(Long userId);
    Object selectByRole(Long id);
}
