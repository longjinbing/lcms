package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品展示实体
 * 表名 shop_goods_gallery
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_goods_gallery")
public class ApiGoodsGallery extends BaseEntity{

            private Long goodsId;
            private String imgUrl;
            private String imgDesc;
            private Long orderNum;
            private Integer status;
    /**
     * 设置：商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：图片
     */
    public String getImgUrl() {
        return imgUrl;
    }
    /**
     * 设置：描述
     */
    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    /**
     * 获取：描述
     */
    public String getImgDesc() {
        return imgDesc;
    }
    /**
     * 设置：排序
     */
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Long getOrderNum() {
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
