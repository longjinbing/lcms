package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 促销广告实体
 * 表名 shop_ad
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_ad")
public class ApiAd extends BaseEntity{

            private Long adPositionId;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer mediaType;
            private String name;
            private String link;
            private String imageUrl;
            private String content;
            private Date endTime;
            private Integer status;
    /**
     * 设置：位置
     */
    public void setAdPositionId(Long adPositionId) {
        this.adPositionId = adPositionId;
    }

    /**
     * 获取：位置
     */
    public Long getAdPositionId() {
        return adPositionId;
    }
    /**
     * 设置：媒体类型
     */
    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 获取：媒体类型
     */
    public Integer getMediaType() {
        return mediaType;
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
     * 设置：链接
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取：链接
     */
    public String getLink() {
        return link;
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
     * 设置：内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取：结束时间
     */
    public Date getEndTime() {
        return endTime;
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
