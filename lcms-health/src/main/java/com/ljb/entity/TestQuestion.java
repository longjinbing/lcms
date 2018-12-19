package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 体质测试问题实体
 * 表名 health_test_question
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@TableName("health_test_question")
public class TestQuestion extends BaseEntity{

            private String question;
            private Long healthId;
            private Integer status;
    /**
     * 设置：问题
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取：问题
     */
    public String getQuestion() {
        return question;
    }
    /**
     * 设置：体质Id
     */
    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    /**
     * 获取：体质Id
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
