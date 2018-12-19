package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

/**
 * 用户角色管理实体
 * 表名 sys_user_role
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_user_role")
public class UserRole extends BaseEntity{

            private Long userId;
            private Long roleId;
    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     */
    public Long getRoleId() {
        return roleId;
    }
}
