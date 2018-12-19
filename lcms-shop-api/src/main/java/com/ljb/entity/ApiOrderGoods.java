package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 订单商品实体
 * 表名 shop_order_goods
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_order_goods")
public class ApiOrderGoods extends BaseEntity{

            private Long orderId;
            private Long goodsId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String goodsName;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String goodsSn;
            private Long productId;
            private Long number;
            private BigDecimal marketPrice;
            private BigDecimal retailPrice;
            private String goodsSpecifitionNameValue;
            private Integer isReal;
            private String goodsSpecifitionIds;
            private String listPicUrl;
            private Integer status;
    /**
     * 设置：订单Id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单Id
     */
    public Long getOrderId() {
        return orderId;
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
     * 设置：商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }
    /**
     * 设置：商品序列号
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    /**
     * 获取：商品序列号
     */
    public String getGoodsSn() {
        return goodsSn;
    }
    /**
     * 设置：产品Id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取：产品Id
     */
    public Long getProductId() {
        return productId;
    }
    /**
     * 设置：商品数量
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * 获取：商品数量
     */
    public Long getNumber() {
        return number;
    }
    /**
     * 设置：市场价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：市场价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }
    /**
     * 设置：零售价格
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取：零售价格
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }
    /**
     * 设置：商品规格详情
     */
    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue;
    }

    /**
     * 获取：商品规格详情
     */
    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }
    /**
     * 设置：虚拟商品
     */
    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    /**
     * 获取：虚拟商品
     */
    public Integer getIsReal() {
        return isReal;
    }
    /**
     * 设置：商品规格Ids
     */
    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds;
    }

    /**
     * 获取：商品规格Ids
     */
    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }
    /**
     * 设置：图片链接
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：图片链接
     */
    public String getListPicUrl() {
        return listPicUrl;
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
