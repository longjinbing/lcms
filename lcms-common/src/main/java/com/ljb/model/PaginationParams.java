package com.ljb.model;

import java.io.Serializable;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/18<br>
 * 描述: <br>
 */
public class PaginationParams implements Serializable {
    private Long offset=0L;
    private Integer limit=10;

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
