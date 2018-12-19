package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品属性管理实体
 * 表名 shop_goods_attribute
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@TableName("shop_goods_attribute")
public class GoodsAttribute extends BaseEntity{

            private Long goodsId;
            private Long attributeId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String value;
            private Integer status;
    /**
     * 设置：商品Id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品Id
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：属性Id
     */
    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * 获取：属性Id
     */
    public Long getAttributeId() {
        return attributeId;
    }
    /**
     * 设置：属性值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：属性值
     */
    public String getValue() {
        return value;
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
