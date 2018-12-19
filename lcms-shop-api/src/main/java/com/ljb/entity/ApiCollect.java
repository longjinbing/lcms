package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品收藏实体
 * 表名 shop_collect
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_collect")
public class ApiCollect extends BaseEntity{

            private Long userId;
            private Long valueId;
            private Long addTime;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer isAttention;
            @NotNull(message = "{field.not.blank}",groups = {AddGroup.class})
    @NotNull(message = "{field.not.blank}",groups = {UpdateGroup.class})
        private Integer typeId;
            private Integer status;
    /**
     * 设置：用户Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户Id
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：产品Id
     */
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    /**
     * 获取：产品Id
     */
    public Long getValueId() {
        return valueId;
    }
    /**
     * 设置：添加时间
     */
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：添加时间
     */
    public Long getAddTime() {
        return addTime;
    }
    /**
     * 设置：是否提醒
     */
    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }

    /**
     * 获取：是否提醒
     */
    public Integer getIsAttention() {
        return isAttention;
    }
    /**
     * 设置：
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取：
     */
    public Integer getTypeId() {
        return typeId;
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
