package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品频道实体
 * 表名 shop_channel
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_channel")
public class ApiChannel extends BaseEntity{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String name;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String url;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String iconUrl;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer orderNum;
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
     * 设置：链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：链接
     */
    public String getUrl() {
        return url;
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
     * 设置：
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：
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
