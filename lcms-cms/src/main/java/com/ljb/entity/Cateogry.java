package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 目录管理实体
 * 表名 cms_cateogry
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-10
 */
@TableName("cms_cateogry")
public class Cateogry extends BaseEntity{

            private Long parentId;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String name;
            private String remark;
            private Integer orderNum;
            private Integer status;
    /**
     * 设置：父Id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父Id
     */
    public Long getParentId() {
        return parentId;
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
     * 设置：
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：
     */
    public String getRemark() {
        return remark;
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
}
