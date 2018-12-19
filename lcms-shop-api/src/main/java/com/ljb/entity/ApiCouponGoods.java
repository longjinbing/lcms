package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 优惠券关联商品实体
 * 表名 shop_coupon_goods
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_coupon_goods")
public class ApiCouponGoods extends BaseEntity{

            private Long couponId;
            private Long goodsId;
            private Integer status;
    /**
     * 设置：优惠券Id
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：优惠券Id
     */
    public Long getCouponId() {
        return couponId;
    }
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
