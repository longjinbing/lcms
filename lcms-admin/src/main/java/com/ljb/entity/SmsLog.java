package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 短信日志管理实体
 * 表名 sys_sms_log
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_sms_log")
public class SmsLog extends BaseEntity{

            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String content;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String phone;
            private String sign;
            private String type;
            private String extno;
            private Long status;
            private String sendId;
            private Long invalidNum;
            private Long successNum;
            private Long blackNum;
            private String returnMsg;
    /**
     * 设置：短信内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：短信内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：接收方
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：接收方
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：用户签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取：用户签名
     */
    public String getSign() {
        return sign;
    }
    /**
     * 设置：类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public String getType() {
        return type;
    }
    /**
     * 设置：扩展码
     */
    public void setExtno(String extno) {
        this.extno = extno;
    }

    /**
     * 获取：扩展码
     */
    public String getExtno() {
        return extno;
    }
    /**
     * 设置：发送状态
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 获取：发送状态
     */
    public Long getStatus() {
        return status;
    }
    /**
     * 设置：发送编号
     */
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    /**
     * 获取：发送编号
     */
    public String getSendId() {
        return sendId;
    }
    /**
     * 设置：失败数量
     */
    public void setInvalidNum(Long invalidNum) {
        this.invalidNum = invalidNum;
    }

    /**
     * 获取：失败数量
     */
    public Long getInvalidNum() {
        return invalidNum;
    }
    /**
     * 设置：失败数量
     */
    public void setSuccessNum(Long successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取：失败数量
     */
    public Long getSuccessNum() {
        return successNum;
    }
    /**
     * 设置：黑名单数
     */
    public void setBlackNum(Long blackNum) {
        this.blackNum = blackNum;
    }

    /**
     * 获取：黑名单数
     */
    public Long getBlackNum() {
        return blackNum;
    }
    /**
     * 设置：返回消息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 获取：返回消息
     */
    public String getReturnMsg() {
        return returnMsg;
    }
}
