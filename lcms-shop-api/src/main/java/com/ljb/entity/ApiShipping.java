package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 快递信息实体
 * 表名 shop_shipping
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_shipping")
public class ApiShipping extends BaseEntity{

            private String code;
            private String name;
            private Integer status;
    /**
     * 设置：
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：
     */
    public String getCode() {
        return code;
    }
    /**
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
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
