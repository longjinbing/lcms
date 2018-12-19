package com.ljb.model;

import com.ljb.entity.Role;

import java.io.Serializable;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/14<br>
 * 描述: <br>
 */
public class RoleDeptModel extends Role implements Serializable {

    public  RoleDeptModel(){
    }

    private Long deptId;

    private String deptName;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
