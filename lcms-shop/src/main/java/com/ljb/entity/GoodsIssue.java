package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 商品承若管理实体
 * 表名 shop_goods_issue
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@TableName("shop_goods_issue")
public class GoodsIssue extends BaseEntity{

            private String question;
            private String answer;
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
     * 设置：回答
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取：回答
     */
    public String getAnswer() {
        return answer;
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
