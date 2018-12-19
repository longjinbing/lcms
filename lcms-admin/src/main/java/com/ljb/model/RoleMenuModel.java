package com.ljb.model;

import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/12<br>
 * 描述: <br>
 */
public class RoleMenuModel {
    private Long roleId;
    private List<Long> menuIds;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
