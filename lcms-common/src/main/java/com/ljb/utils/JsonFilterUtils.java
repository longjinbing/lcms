package com.ljb.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonFilterUtils {

    //递归对象
    public static JSONObject changeObjectUrlMsg(String suffix,JSONObject jsonObject) {
    	if(jsonObject.containsKey("action")) {
    		updateUrl(suffix,jsonObject);
    	}
    	if(jsonObject.containsKey("list")) {
    		JSONArray array=jsonObject.getJSONArray("list");
    		for(int i=0;i<array.size();i++) {
    			if(array.getJSONObject(i).containsKey("action")) {
    				updateUrl(suffix,array.getJSONObject(i));
    			}
    		}
    	}
    	return jsonObject;
    }

    public static void updateUrl(String suffix, JSONObject jsonObject) {
    	String url=jsonObject.getString("action");
		url=suffix+(url=="null"?"":url);
		jsonObject.remove("action");
		jsonObject.put("action", url);
    }
}

