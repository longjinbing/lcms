package com.ljb.entity;

import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljb.Base.*;
import com.ljb.model.*;
import java.util.*;

/**
 * 体质测试结果详情实体
 * 表名 health_test_result_details
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@TableName("health_test_result_details")
public class TestResultDetails extends BaseEntity{

            private Long resultId;
            private Long constitutionId;
            private Integer result;
            private Integer status;
    /**
     * 设置：结果id
     */
    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    /**
     * 获取：结果id
     */
    public Long getResultId() {
        return resultId;
    }
    /**
     * 设置：体质id
     */
    public void setConstitutionId(Long constitutionId) {
        this.constitutionId = constitutionId;
    }

    /**
     * 获取：体质id
     */
    public Long getConstitutionId() {
        return constitutionId;
    }
    /**
     * 设置：分值
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 获取：分值
     */
    public Integer getResult() {
        return result;
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
