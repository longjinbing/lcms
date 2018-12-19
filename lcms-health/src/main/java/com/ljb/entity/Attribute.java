package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 体质属性实体
 * 表名 health_attribute
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@TableName("health_attribute")
public class Attribute extends BaseEntity{

            private String name;
            private String content;
            private Long healthId;
            private Integer orderNum;
            private Integer status;
    /**
     * 设置：属性名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：属性名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：属性内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：属性内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：
     */
    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    /**
     * 获取：
     */
    public Long getHealthId() {
        return healthId;
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
