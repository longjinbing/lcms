package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 用户足迹实体
 * 表名 shop_footprint
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_footprint")
public class ApiFootprint extends BaseEntity{

            private Long goodsId;
            private Long referrer;
            private Integer status;
    /**
     * 设置：商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：转发人
     */
    public void setReferrer(Long referrer) {
        this.referrer = referrer;
    }

    /**
     * 获取：转发人
     */
    public Long getReferrer() {
        return referrer;
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
