package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 文章评论实体
 * 表名 cms_article_comment
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("cms_article_comment")
public class ApiArticleComment extends BaseEntity{

            private Long parentId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String content;
            private String ip;
            private Integer status;
    /**
     * 设置：父评论ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父评论ID
     */
    public Long getParentId() {
        return parentId;
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
     * 设置：Ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取：Ip
     */
    public String getIp() {
        return ip;
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
}
