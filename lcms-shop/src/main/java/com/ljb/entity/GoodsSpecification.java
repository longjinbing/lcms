package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品规格管理实体
 * 表名 shop_goods_specification
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@TableName("shop_goods_specification")
public class GoodsSpecification extends BaseEntity{

            private Long goodsId;
            private Long specificationId;
            private String value;
            private String imageUrl;
            private Integer status;
    /**
     * 设置：商品
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：规格
     */
    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    /**
     * 获取：规格
     */
    public Long getSpecificationId() {
        return specificationId;
    }
    /**
     * 设置：值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：值
     */
    public String getValue() {
        return value;
    }
    /**
     * 设置：图片
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取：图片
     */
    public String getImageUrl() {
        return imageUrl;
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
