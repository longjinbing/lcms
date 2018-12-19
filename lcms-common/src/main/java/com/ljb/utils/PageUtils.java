package com.ljb.utils;


import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<?> rows;
    private Long offset;
    private Integer limit;

    public PageUtils(List<?> rows, Long total, Long offset,Integer limit){
        this.rows=rows;
        this.total=total;
        this.offset=offset;
        this.limit=limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

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
