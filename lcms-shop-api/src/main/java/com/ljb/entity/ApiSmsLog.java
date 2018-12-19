package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 短信日志实体
 * 表名 shop_sms_log
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@TableName("shop_sms_log")
public class ApiSmsLog extends BaseEntity{

            private Long userId;
            private String phone;
            private Long logDate;
            private String smsCode;
            private Long sendStatus;
            private String smsText;
            private Integer status;
    /**
     * 设置：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：手机号
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：时间
     */
    public void setLogDate(Long logDate) {
        this.logDate = logDate;
    }

    /**
     * 获取：时间
     */
    public Long getLogDate() {
        return logDate;
    }
    /**
     * 设置：验证码
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    /**
     * 获取：验证码
     */
    public String getSmsCode() {
        return smsCode;
    }
    /**
     * 设置：状态
     */
    public void setSendStatus(Long sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取：状态
     */
    public Long getSendStatus() {
        return sendStatus;
    }
    /**
     * 设置：内容
     */
    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    /**
     * 获取：内容
     */
    public String getSmsText() {
        return smsText;
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
