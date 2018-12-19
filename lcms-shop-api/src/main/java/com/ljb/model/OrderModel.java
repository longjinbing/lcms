package com.ljb.model;

import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/18<br>
 * 描述: <br>
 */
public class OrderModel {
    private String province;
    private String city;
    private String street;
    private String address;
    private String name;
    private String phone;
    private String payType;
    private Map<String,Object> orderInfo;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Map<String, Object> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Map<String, Object> orderInfo) {
        this.orderInfo = orderInfo;
    }
}
