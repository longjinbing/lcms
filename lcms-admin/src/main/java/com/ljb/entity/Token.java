package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 在线用户管理实体
 * 表名 sys_token
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@TableName("sys_token")
public class Token extends BaseEntity{

            private Date expireTime;
            private String ticket;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String remark;
            @NotBlank(message = "{field.not.blank}",groups = {AddGroup.class})
        @NotBlank(message = "{field.not.blank}",groups = {UpdateGroup.class})
            private String ip;
            private String principle;
    /**
     * 设置：过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取：过期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }
    /**
     * 设置：票据
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * 获取：票据
     */
    public String getTicket() {
        return ticket;
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
     * 设置：授权
     */
    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    /**
     * 获取：授权
     */
    public String getPrinciple() {
        return principle;
    }
}
