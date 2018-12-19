package com.ljb.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/16<br>
 * 描述: <br>
 */
public class QuestionModel implements Serializable {

    private String name;
    private String phone;
    private Integer sex;
    private Map<Long,String> answers;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
