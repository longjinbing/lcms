package com.ljb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.BaseEntity;

/**
 * 系统日志管理实体
 * 表名 sys_log
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_log")
public class Log extends BaseEntity{

            private String operation;
            private String method;
            private String params;
            private String ip;
            private String url;
            private String remark;
            private Integer level;
            private String errorStack;
            private Integer status;
    /**
     * 设置：用户操作
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 获取：用户操作
     */
    public String getOperation() {
        return operation;
    }
    /**
     * 设置：请求方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取：请求方法
     */
    public String getMethod() {
        return method;
    }
    /**
     * 设置：请求参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取：请求参数
     */
    public String getParams() {
        return params;
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
     * 设置：URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：URL
     */
    public String getUrl() {
        return url;
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
     * 设置：级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取：级别
     */
    public Integer getLevel() {
        return level;
    }
    /**
     * 设置：错误堆栈信息
     */
    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    /**
     * 获取：错误堆栈信息
     */
    public String getErrorStack() {
        return errorStack;
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
