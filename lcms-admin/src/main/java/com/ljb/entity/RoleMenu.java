package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 角色菜单管理实体
 * 表名 sys_role_menu
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_role_menu")
public class RoleMenu extends BaseEntity{

            private Long roleId;
            private Long menuId;
    /**
     * 设置：角色
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色
     */
    public Long getRoleId() {
        return roleId;
    }
    /**
     * 设置：菜单
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：菜单
     */
    public Long getMenuId() {
        return menuId;
    }
}
