package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 品牌管理实体
 * 表名 shop_brand
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_brand")
public class ApiBrand extends BaseEntity
{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String name;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String listPicUrl;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String simpleDesc;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String imageUrl;
            private BigDecimal floorPrice;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String appListPicUrl;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer isNew;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String newPicUrl;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer orderNum;
            private Integer status;
    /**
     * 设置：品牌名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：品牌名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：图片
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：图片
     */
    public String getListPicUrl() {
        return listPicUrl;
    }
    /**
     * 设置：描述
     */
    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    /**
     * 获取：描述
     */
    public String getSimpleDesc() {
        return simpleDesc;
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
     * 设置：价格
     */
    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    /**
     * 获取：价格
     */
    public BigDecimal getFloorPrice() {
        return floorPrice;
    }
    /**
     * 设置：app显示图片
     */
    public void setAppListPicUrl(String appListPicUrl) {
        this.appListPicUrl = appListPicUrl;
    }

    /**
     * 获取：app显示图片
     */
    public String getAppListPicUrl() {
        return appListPicUrl;
    }
    /**
     * 设置：新品牌
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 获取：新品牌
     */
    public Integer getIsNew() {
        return isNew;
    }
    /**
     * 设置：图片
     */
    public void setNewPicUrl(String newPicUrl) {
        this.newPicUrl = newPicUrl;
    }

    /**
     * 获取：图片
     */
    public String getNewPicUrl() {
        return newPicUrl;
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
