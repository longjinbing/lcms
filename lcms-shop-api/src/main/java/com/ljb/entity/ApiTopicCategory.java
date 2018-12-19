package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 主题类型实体
 * 表名 shop_topic_category
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_topic_category")
public class ApiTopicCategory extends BaseEntity{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String title;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String picUrl;
            private Integer status;
    /**
     * 设置：活动类别主题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：活动类别主题
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：活动类别图片链接
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取：活动类别图片链接
     */
    public String getPicUrl() {
        return picUrl;
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
