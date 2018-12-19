package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

/**
 * 菜单管理实体
 * 表名 sys_menu
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private Long parentId;
    private String name;
    private String url;
    private Integer type;
    private String icon;
    private Integer orderNum;
    private Integer status;

    /**
     * 设置：上级菜单
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级菜单
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：菜单URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：菜单图标
     */
    public String getIcon() {
        return icon;
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

    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        return ((Menu)obj).getId().equals(getId());
    }
}
