package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 用户地址实体
 * 表名 shop_address
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_address")
public class ApiAddress extends BaseEntity{

            private Long userId;
            private String userName;
            private String telNumber;
            private String postalCode;
            private String nationalCode;
            private String provinceName;
            private String cityName;
            private String countyName;
            private String detailInfo;
            private Integer status;
    /**
     * 设置：会员ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：收货人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：收货人姓名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：手机
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * 获取：手机
     */
    public String getTelNumber() {
        return telNumber;
    }
    /**
     * 设置：邮编
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 获取：邮编
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * 设置：收货地址国家码
     */
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    /**
     * 获取：收货地址国家码
     */
    public String getNationalCode() {
        return nationalCode;
    }
    /**
     * 设置：省
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取：省
     */
    public String getProvinceName() {
        return provinceName;
    }
    /**
     * 设置：市
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取：市
     */
    public String getCityName() {
        return cityName;
    }
    /**
     * 设置：区
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    /**
     * 获取：区
     */
    public String getCountyName() {
        return countyName;
    }
    /**
     * 设置：详细收货地址信息
     */
    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    /**
     * 获取：详细收货地址信息
     */
    public String getDetailInfo() {
        return detailInfo;
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
