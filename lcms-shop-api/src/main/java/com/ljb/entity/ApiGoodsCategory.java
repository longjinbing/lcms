package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品分类实体
 * 表名 shop_goods_category
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_goods_category")
public class ApiGoodsCategory extends BaseEntity{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String name;
            private String keywords;
            private String frontDesc;
            private Long parentId;
            private Integer orderNum;
            private Integer showIndex;
            private String bannerUrl;
            private String iconUrl;
            private String imgUrl;
            private String wapBannerUrl;
            private String level;
            private Long type;
            private String frontName;
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
     * 设置：介绍
     */
    public void setFrontDesc(String frontDesc) {
        this.frontDesc = frontDesc;
    }

    /**
     * 获取：介绍
     */
    public String getFrontDesc() {
        return frontDesc;
    }
    /**
     * 设置：上级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级ID
     */
    public Long getParentId() {
        return parentId;
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
     * 设置：
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    /**
     * 获取：
     */
    public Integer getShowIndex() {
        return showIndex;
    }
    /**
     * 设置：轮播图片
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 获取：轮播图片
     */
    public String getBannerUrl() {
        return bannerUrl;
    }
    /**
     * 设置：图标
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取：图标
     */
    public String getIconUrl() {
        return iconUrl;
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
     * 设置：app轮播图
     */
    public void setWapBannerUrl(String wapBannerUrl) {
        this.wapBannerUrl = wapBannerUrl;
    }

    /**
     * 获取：app轮播图
     */
    public String getWapBannerUrl() {
        return wapBannerUrl;
    }
    /**
     * 设置：
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取：
     */
    public String getLevel() {
        return level;
    }
    /**
     * 设置：类型
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public Long getType() {
        return type;
    }
    /**
     * 设置：名称
     */
    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    /**
     * 获取：名称
     */
    public String getFrontName() {
        return frontName;
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
