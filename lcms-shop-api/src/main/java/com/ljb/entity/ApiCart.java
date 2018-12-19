package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 购物缓存实体
 * 表名 shop_cart
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_cart")
public class ApiCart extends BaseEntity {

            private Long userId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String sessionId;
            private Long goodsId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String goodsSn;
            private Long productId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String goodsName;
            private BigDecimal marketPrice;
            private BigDecimal retailPrice;
            private Long number;
            private String goodsSpecifitionNameValue;
            private String goodsSpecifitionIds;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer checked;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String listPicUrl;
            private Integer status;
    /**
     * 设置：会员Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员Id
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取：sessionId
     */
    public String getSessionId() {
        return sessionId;
    }
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
     * 设置：产品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：产品名称
     */
    public String getGoodsName() {
        return goodsName;
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
     * 设置：数量
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * 获取：数量
     */
    public Long getNumber() {
        return number;
    }
    /**
     * 设置：规格属性组成的字符串，用来显示用
     */
    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue;
    }

    /**
     * 获取：规格属性组成的字符串，用来显示用
     */
    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }
    /**
     * 设置：product表对应的goods_specifition_ids
     */
    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds;
    }

    /**
     * 获取：product表对应的goods_specifition_ids
     */
    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }
    /**
     * 设置：
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * 获取：
     */
    public Integer getChecked() {
        return checked;
    }
    /**
     * 设置：商品图片
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：商品图片
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
