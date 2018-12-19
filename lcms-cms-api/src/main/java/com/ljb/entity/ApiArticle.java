package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 文章管理实体
 * 表名 cms_article
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("cms_article")
public class ApiArticle extends BaseEntity{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String title;
            private String image;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String content;
            private Integer viewCount;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer orderNum;
            private Integer status;
            private Long categoryId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String author;
            private Date publishTime;
            private Integer commentable;
            private String attachment;
    /**
     * 设置：文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：文章标题
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：图片url
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取：图片url
     */
    public String getImage() {
        return image;
    }
    /**
     * 设置：文件内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：文件内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：浏览数
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取：浏览数
     */
    public Integer getViewCount() {
        return viewCount;
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
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：类别Id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：类别Id
     */
    public Long getCategoryId() {
        return categoryId;
    }
    /**
     * 设置：作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取：作者
     */
    public String getAuthor() {
        return author;
    }
    /**
     * 设置：发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取：发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }
    /**
     * 设置：能否评价
     */
    public void setCommentable(Integer commentable) {
        this.commentable = commentable;
    }

    /**
     * 获取：能否评价
     */
    public Integer getCommentable() {
        return commentable;
    }
    /**
     * 设置：附件地址
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * 获取：附件地址
     */
    public String getAttachment() {
        return attachment;
    }
}
