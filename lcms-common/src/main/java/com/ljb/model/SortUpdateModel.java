package com.ljb.model;

import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/13<br>
 * 描述: <br>
 */
public class SortUpdateModel {
    private Long id;
    private Integer index;
    private Map<String,Object> queryParams;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }
}
