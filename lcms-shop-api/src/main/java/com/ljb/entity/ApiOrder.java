package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单管理实体
 * 表名 shop_order
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_order")
public class ApiOrder extends BaseEntity {

            private String orderSn;
            private Long userId;
            private Integer shippingStatus;
            private String consignee;
            private String country;
            private String province;
            private String city;
            private String district;
            private String address;
            private String phone;
            private String postscript;
            private Integer shippingId;
            private String shippingName;
            private String payId;
            private String payName;
            private BigDecimal shippingFee;
            private BigDecimal actualPrice;
            private Integer integral;
            private BigDecimal integralMoney;
            private BigDecimal orderPrice;
            private BigDecimal goodsPrice;
            private Date confirmTime;
            private Date payTime;
            private Integer freightPrice;
            private Long couponId;
            private Long parentId;
            private BigDecimal couponPrice;
            private String callbackStatus;
            private String shippingNo;
            private BigDecimal fullCutPrice;
            private String orderType;
            private Integer status;
    /**
     * 设置：商品SN
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取：商品SN
     */
    public String getOrderSn() {
        return orderSn;
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
     * 设置：邮寄状态
     */
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 获取：邮寄状态
     */
    public Integer getShippingStatus() {
        return shippingStatus;
    }
    /**
     * 设置：收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取：收货人
     */
    public String getConsignee() {
        return consignee;
    }
    /**
     * 设置：国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：国家
     */
    public String getCountry() {
        return country;
    }
    /**
     * 设置：省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：省
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：市
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取：区
     */
    public String getDistrict() {
        return district;
    }
    /**
     * 设置：详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：详细地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：手机
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：邮编
     */
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    /**
     * 获取：邮编
     */
    public String getPostscript() {
        return postscript;
    }
    /**
     * 设置：快递编号
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * 获取：快递编号
     */
    public Integer getShippingId() {
        return shippingId;
    }
    /**
     * 设置：快递名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取：快递名称
     */
    public String getShippingName() {
        return shippingName;
    }
    /**
     * 设置：支付ID
     */
    public void setPayId(String payId) {
        this.payId = payId;
    }

    /**
     * 获取：支付ID
     */
    public String getPayId() {
        return payId;
    }
    /**
     * 设置：支付名称
     */
    public void setPayName(String payName) {
        this.payName = payName;
    }

    /**
     * 获取：支付名称
     */
    public String getPayName() {
        return payName;
    }
    /**
     * 设置：邮费
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 获取：邮费
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }
    /**
     * 设置：实际需要支付的金额
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * 获取：实际需要支付的金额
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }
    /**
     * 设置：合计
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取：合计
     */
    public Integer getIntegral() {
        return integral;
    }
    /**
     * 设置：总价
     */
    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    /**
     * 获取：总价
     */
    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }
    /**
     * 设置：订单总价
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取：订单总价
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
    /**
     * 设置：商品总价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品总价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    /**
     * 设置：确认时间
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取：确认时间
     */
    public Date getConfirmTime() {
        return confirmTime;
    }
    /**
     * 设置：支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取：支付时间
     */
    public Date getPayTime() {
        return payTime;
    }
    /**
     * 设置：配送费用
     */
    public void setFreightPrice(Integer freightPrice) {
        this.freightPrice = freightPrice;
    }

    /**
     * 获取：配送费用
     */
    public Integer getFreightPrice() {
        return freightPrice;
    }
    /**
     * 设置：使用的优惠券id
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：使用的优惠券id
     */
    public Long getCouponId() {
        return couponId;
    }
    /**
     * 设置：
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取：
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
    /**
     * 设置：
     */
    public void setCallbackStatus(String callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     * 获取：
     */
    public String getCallbackStatus() {
        return callbackStatus;
    }
    /**
     * 设置：
     */
    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    /**
     * 获取：
     */
    public String getShippingNo() {
        return shippingNo;
    }
    /**
     * 设置：
     */
    public void setFullCutPrice(BigDecimal fullCutPrice) {
        this.fullCutPrice = fullCutPrice;
    }

    /**
     * 获取：
     */
    public BigDecimal getFullCutPrice() {
        return fullCutPrice;
    }
    /**
     * 设置：
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取：
     */
    public String getOrderType() {
        return orderType;
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
