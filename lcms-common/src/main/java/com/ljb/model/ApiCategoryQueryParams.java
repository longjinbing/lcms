package com.ljb.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/18<br>
 * 描述: <br>
 */
public class ApiCategoryQueryParams extends PaginationParams {

    private Long userId;

    public Map<String,Object> query(){
        Map<String,Object> map=new HashMap<>();
        map.put("offset",getOffset() );
        map.put("limit",getLimit() );
        map.put("categoryId",userId );
        return map;
    }

    public Long getCategoryId() {
        return userId;
    }

    public void setCategoryId(Long categoryId) {
        this.userId = categoryId;
    }
}
