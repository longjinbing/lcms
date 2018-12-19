package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 部门管理实体
 * 表名 sys_dept
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-12
 */
@TableName("sys_dept")
public class Dept extends BaseEntity{

            private Long parentId;
            private String remark;
            private String name;
            private String contactName;
            private String contactPhone;
            private Integer orderNum;
    /**
     * 设置：上级部门
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级部门
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：职责
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：职责
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：负责人
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取：负责人
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * 设置：电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取：电话
     */
    public String getContactPhone() {
        return contactPhone;
    }
    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }
}
