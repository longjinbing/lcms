package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

/**
 * 系统配置管理实体
 * 表名 sys_config
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_config")
public class Config extends BaseEntity{

            private Long parentId;
            private String item;
            private String value;
            private Integer status;
            private String remark;
    /**
     * 设置：配置名称
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：配置名称
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：键名
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * 获取：键名
     */
    public String getItem() {
        return item;
    }
    /**
     * 设置：键值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：键值
     */
    public String getValue() {
        return value;
    }
    /**
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
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
}
