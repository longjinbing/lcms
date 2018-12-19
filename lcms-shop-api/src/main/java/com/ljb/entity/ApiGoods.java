package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品管理实体
 * 表名 shop_goods
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_goods")
public class ApiGoods extends BaseEntity {

            private Long categoryId;
            private String goodsSn;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String name;
            private Long brandId;
            private Long goodsNumber;
            private String keywords;
            private String goodsBrief;
            private String goodsDesc;
            private Date addTime;
            private Integer orderNum;
            private Long attributeCategory;
            private BigDecimal counterPrice;
            private BigDecimal extraPrice;
            private Integer isNew;
            private String goodsUnit;
            private String primaryPicUrl;
            private String listPicUrl;
            private BigDecimal retailPrice;
            private Long sellVolume;
            private Long primaryProductId;
            private BigDecimal unitPrice;
            private String promotionDesc;
            private String promotionTag;
            private BigDecimal appExclusivePrice;
            private Integer isAppExclusive;
            private Integer isLimited;
            private Integer isHot;
            private BigDecimal marketPrice;
            private Long createUserDeptId;
            private Integer status;
    /**
     * 设置：分类
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：分类
     */
    public Long getCategoryId() {
        return categoryId;
    }
    /**
     * 设置：SN编码
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    /**
     * 获取：SN编码
     */
    public String getGoodsSn() {
        return goodsSn;
    }
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
     * 设置：品牌
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取：品牌
     */
    public Long getBrandId() {
        return brandId;
    }
    /**
     * 设置：库存量
     */
    public void setGoodsNumber(Long goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * 获取：库存量
     */
    public Long getGoodsNumber() {
        return goodsNumber;
    }
    /**
     * 设置：关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取：关键字
     */
    public String getKeywords() {
        return keywords;
    }
    /**
     * 设置：好评度
     */
    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    /**
     * 获取：好评度
     */
    public String getGoodsBrief() {
        return goodsBrief;
    }
    /**
     * 设置：商品描述
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * 获取：商品描述
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }
    /**
     * 设置：上架时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：上架时间
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }
    /**
     * 设置：属性分类
     */
    public void setAttributeCategory(Long attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    /**
     * 获取：属性分类
     */
    public Long getAttributeCategory() {
        return attributeCategory;
    }
    /**
     * 设置：专柜价格
     */
    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    /**
     * 获取：专柜价格
     */
    public BigDecimal getCounterPrice() {
        return counterPrice;
    }
    /**
     * 设置：附加价格
     */
    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    /**
     * 获取：附加价格
     */
    public BigDecimal getExtraPrice() {
        return extraPrice;
    }
    /**
     * 设置：是否上新
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 获取：是否上新
     */
    public Integer getIsNew() {
        return isNew;
    }
    /**
     * 设置：商品单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取：商品单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }
    /**
     * 设置：商品主图
     */
    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl;
    }

    /**
     * 获取：商品主图
     */
    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }
    /**
     * 设置：商品列表图
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：商品列表图
     */
    public String getListPicUrl() {
        return listPicUrl;
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
     * 设置：销售量
     */
    public void setSellVolume(Long sellVolume) {
        this.sellVolume = sellVolume;
    }

    /**
     * 获取：销售量
     */
    public Long getSellVolume() {
        return sellVolume;
    }
    /**
     * 设置：主sku　product_id
     */
    public void setPrimaryProductId(Long primaryProductId) {
        this.primaryProductId = primaryProductId;
    }

    /**
     * 获取：主sku　product_id
     */
    public Long getPrimaryProductId() {
        return primaryProductId;
    }
    /**
     * 设置：单位价格，单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取：单位价格，单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    /**
     * 设置：推广信息
     */
    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    /**
     * 获取：推广信息
     */
    public String getPromotionDesc() {
        return promotionDesc;
    }
    /**
     * 设置：推广标签
     */
    public void setPromotionTag(String promotionTag) {
        this.promotionTag = promotionTag;
    }

    /**
     * 获取：推广标签
     */
    public String getPromotionTag() {
        return promotionTag;
    }
    /**
     * 设置：APP专享价
     */
    public void setAppExclusivePrice(BigDecimal appExclusivePrice) {
        this.appExclusivePrice = appExclusivePrice;
    }

    /**
     * 获取：APP专享价
     */
    public BigDecimal getAppExclusivePrice() {
        return appExclusivePrice;
    }
    /**
     * 设置：是否是APP专属
     */
    public void setIsAppExclusive(Integer isAppExclusive) {
        this.isAppExclusive = isAppExclusive;
    }

    /**
     * 获取：是否是APP专属
     */
    public Integer getIsAppExclusive() {
        return isAppExclusive;
    }
    /**
     * 设置：
     */
    public void setIsLimited(Integer isLimited) {
        this.isLimited = isLimited;
    }

    /**
     * 获取：
     */
    public Integer getIsLimited() {
        return isLimited;
    }
    /**
     * 设置：
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    /**
     * 获取：
     */
    public Integer getIsHot() {
        return isHot;
    }
    /**
     * 设置：
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }
    /**
     * 设置：
     */
    public void setCreateUserDeptId(Long createUserDeptId) {
        this.createUserDeptId = createUserDeptId;
    }

    /**
     * 获取：
     */
    public Long getCreateUserDeptId() {
        return createUserDeptId;
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
