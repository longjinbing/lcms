package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品属性类型管理实体
 * 表名 shop_goods_attribute_category
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@TableName("shop_goods_attribute_category")
public class GoodsAttributeCategory extends BaseEntity{

            private Long attributeCategoryId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String name;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer inputType;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String value;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer orderNum;
            private Integer status;
    /**
     * 设置：商品类型
     */
    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    /**
     * 获取：商品类型
     */
    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }
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
     * 设置：当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入
     */
    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    /**
     * 获取：当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入
     */
    public Integer getInputType() {
        return inputType;
    }
    /**
     * 设置：即选择输入,则attr_name对应的值的取值就是该这字段值 
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：即选择输入,则attr_name对应的值的取值就是该这字段值 
     */
    public String getValue() {
        return value;
    }
    /**
     * 设置：
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：
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
