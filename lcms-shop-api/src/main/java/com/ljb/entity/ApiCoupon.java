package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品优惠卷实体
 * 表名 shop_coupon
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_coupon")
public class ApiCoupon extends BaseEntity {

            private String name;
            private BigDecimal typeMoney;
            private Integer sendType;
            private BigDecimal minAmount;
            private BigDecimal maxAmount;
            private Date sendStartDate;
            private Date sendEndDate;
            private Date useStartDate;
            private Date useEndDate;
            private BigDecimal minGoodsAmount;
            private Integer minTransmitNum;
            private Integer status;
    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：价值
     */
    public void setTypeMoney(BigDecimal typeMoney) {
        this.typeMoney = typeMoney;
    }

    /**
     * 获取：价值
     */
    public BigDecimal getTypeMoney() {
        return typeMoney;
    }
    /**
     * 设置：类型
     */
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    /**
     * 获取：类型
     */
    public Integer getSendType() {
        return sendType;
    }
    /**
     * 设置：最小数量
     */
    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 获取：最小数量
     */
    public BigDecimal getMinAmount() {
        return minAmount;
    }
    /**
     * 设置：最大数量
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * 获取：最大数量
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }
    /**
     * 设置：有效领卷日期
     */
    public void setSendStartDate(Date sendStartDate) {
        this.sendStartDate = sendStartDate;
    }

    /**
     * 获取：有效领卷日期
     */
    public Date getSendStartDate() {
        return sendStartDate;
    }
    /**
     * 设置：无效领卷日期
     */
    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    /**
     * 获取：无效领卷日期
     */
    public Date getSendEndDate() {
        return sendEndDate;
    }
    /**
     * 设置：开始使用日期
     */
    public void setUseStartDate(Date useStartDate) {
        this.useStartDate = useStartDate;
    }

    /**
     * 获取：开始使用日期
     */
    public Date getUseStartDate() {
        return useStartDate;
    }
    /**
     * 设置：结束使用日期
     */
    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    /**
     * 获取：结束使用日期
     */
    public Date getUseEndDate() {
        return useEndDate;
    }
    /**
     * 设置：最小可用
     */
    public void setMinGoodsAmount(BigDecimal minGoodsAmount) {
        this.minGoodsAmount = minGoodsAmount;
    }

    /**
     * 获取：最小可用
     */
    public BigDecimal getMinGoodsAmount() {
        return minGoodsAmount;
    }
    /**
     * 设置：转发次数
     */
    public void setMinTransmitNum(Integer minTransmitNum) {
        this.minTransmitNum = minTransmitNum;
    }

    /**
     * 获取：转发次数
     */
    public Integer getMinTransmitNum() {
        return minTransmitNum;
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
