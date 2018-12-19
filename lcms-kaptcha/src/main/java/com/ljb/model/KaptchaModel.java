package com.ljb.model;

import java.io.Serializable;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/29<br>
 * 描述: <br>
 */
public class KaptchaModel implements Serializable {
    private String text;
    private Long createTime;
    private Long validityTime;

    public KaptchaModel(){}

    public KaptchaModel(String text, Long createTime){
        this.text=text;
        this.createTime=createTime;
        this.validityTime=900L;
    }

    public KaptchaModel(String text, Long createTime, Long validityTime){
        this.text=text;
        this.createTime=createTime;
        this.validityTime=validityTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Long validityTime) {
        this.validityTime = validityTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
