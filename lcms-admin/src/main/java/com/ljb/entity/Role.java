package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 角色管理实体
 * 表名 sys_role
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-12
 */
@TableName("sys_role")
public class Role extends BaseEntity{

            private String name;
            private Long deptId;
            private String remark;
            private String description;
    /**
     * 设置：角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：角色名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：
     */
    public Long getDeptId() {
        return deptId;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取：描述
     */
    public String getDescription() {
        return description;
    }
}
