package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.RoleMenu;
import com.ljb.model.RoleMenuModel;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface RoleMenuService extends BaseService<RoleMenu,Long> {
    Boolean saveRoleMenu(RoleMenuModel roleMenuModel);

    List<Map<String,Object>> roleHasMenuList(Long roleId);
}
