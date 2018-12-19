package com.ljb.model;

import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/12<br>
 * 描述: <br>
 */
public class UserRoleModel {
    private Long userId;
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
