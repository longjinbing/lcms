package com.ljb.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/18<br>
 * 描述: <br>
 */
public class ApiUserQueryParams extends PaginationParams {

    private Long categoryId;

    public Map<String,Object> query(){
        Map<String,Object> map=new HashMap<>();
        map.put("offset",getOffset() );
        map.put("limit",getLimit() );
        map.put("categoryId",categoryId );
        return map;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
