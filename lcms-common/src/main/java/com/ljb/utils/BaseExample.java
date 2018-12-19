package com.ljb.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/10<br>
 * 描述: <br>
 */
public abstract class BaseExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<GeneratedCriteria> oredCriteria;

    protected Long offset = 0L;

    protected int limit = 10;

    public BaseExample() {
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<GeneratedCriteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(GeneratedCriteria criteria) {
        oredCriteria.add(criteria);
    }

    public GeneratedCriteria or() {
        GeneratedCriteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public GeneratedCriteria createCriteria() {
        GeneratedCriteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 子类需实现该方法
     *
     * @return
     */
    protected abstract GeneratedCriteria createCriteriaInternal();
}
