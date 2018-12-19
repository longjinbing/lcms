package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 用户优惠卷实体
 * 表名 shop_user_coupon
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_user_coupon")
public class ApiUserCoupon extends BaseEntity{

            private Integer couponId;
            private String couponNumber;
            private Long userId;
            private Date usedTime;
            private Date addTime;
            private Long orderId;
            private String sourceKey;
            private Integer referrer;
            private Integer status;
    /**
     * 设置：
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：
     */
    public Integer getCouponId() {
        return couponId;
    }
    /**
     * 设置：
     */
    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    /**
     * 获取：
     */
    public String getCouponNumber() {
        return couponNumber;
    }
    /**
     * 设置：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    /**
     * 获取：
     */
    public Date getUsedTime() {
        return usedTime;
    }
    /**
     * 设置：
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：
     */
    public Long getOrderId() {
        return orderId;
    }
    /**
     * 设置：来源key
     */
    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    /**
     * 获取：来源key
     */
    public String getSourceKey() {
        return sourceKey;
    }
    /**
     * 设置：发券人
     */
    public void setReferrer(Integer referrer) {
        this.referrer = referrer;
    }

    /**
     * 获取：发券人
     */
    public Integer getReferrer() {
        return referrer;
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
