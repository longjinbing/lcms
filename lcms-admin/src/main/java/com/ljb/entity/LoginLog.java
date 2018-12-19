package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 登录日志管理实体
 * 表名 sys_login_log
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_login_log")
public class LoginLog extends BaseEntity{

            private Integer status;
            private String ip;
            private String device;
            private String remark;
            private String result;
    /**
     * 设置：用户状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：用户状态
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取：IP地址
     */
    public String getIp() {
        return ip;
    }
    /**
     * 设置：登陆来源
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * 获取：登陆来源
     */
    public String getDevice() {
        return device;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：结果
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取：结果
     */
    public String getResult() {
        return result;
    }
}
