package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 体质测试结果实体
 * 表名 health_test_result
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@TableName("health_test_result")
public class TestResult extends BaseEntity{

            private Long userId;
            private String username;
            private String phone;
            private Integer sex;
            private Long healthId;
            private Integer status;
    /**
     * 设置：用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：姓名
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：联系方式
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取：性别
     */
    public Integer getSex() {
        return sex;
    }
    /**
     * 设置：体质id
     */
    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    /**
     * 获取：体质id
     */
    public Long getHealthId() {
        return healthId;
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
