package com.ljb.utils;

import com.ljb.model.SortUpdateModel;

import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/13<br>
 * 描述: <br>
 */
public class SortUtils {

    public static Map<String,Object> createTopQueryMap(SortUpdateModel sortUpdateModel){
        Map<String,Object> map=sortUpdateModel.getQueryParams();;
        map.put("limit",1);
        map.put("offset",0);
        return map;
    }

    public static Map<String,Object> createUpQueryMap(SortUpdateModel sortUpdateModel){
        Map<String,Object> map=sortUpdateModel.getQueryParams();
        map.put("limit",1 );
        map.put("offset", sortUpdateModel.getIndex()-1>-1?sortUpdateModel.getIndex()-1:0);
        return map;
    }

    public static Map<String,Object> createDownQueryMap(SortUpdateModel sortUpdateModel){
        Map<String,Object> map=sortUpdateModel.getQueryParams();
        map.put("limit",1 );
        map.put("offset", sortUpdateModel.getIndex()+1>-1?sortUpdateModel.getIndex()+1:0);
        return map;
    }

    public static Long getId(List<Map<String,Object>> list){
        return Long.valueOf(list.get(0).getOrDefault("id","1").toString());
    }

    public static Integer getOrderNum(List<Map<String,Object>> list){
        if(list==null||list.size()==0)
            return null;
        Integer orderNum=Integer.valueOf(list.get(0).getOrDefault("orderNum","1").toString());
        return orderNum;
    }



}
