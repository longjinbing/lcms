package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

/**
 * 体质分类实体
 * 表名 health_category
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@TableName("health_category")
public class Category extends BaseEntity{

            private String name;
            private String remark;
            private Integer status;
    /**
     * 设置：体质名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：体质名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：简单描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：简单描述
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }
}
