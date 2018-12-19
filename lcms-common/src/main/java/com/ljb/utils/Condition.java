package com.ljb.utils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */
public class Condition extends HashMap<String, Object> {

    protected Condition() {

    }

    public Condition eq(String key, Object value) {
        this.put(originalColumn(key), value);
        return this;
    }

    public Condition eqType(Integer type){
        return eq("type", type);
    }

    public Condition eqStatus(Integer status){
        return eq("status", status);
    }

    public Condition inType(Integer... types){
        return eq("type", Arrays.asList(types));
    }

    public Condition inStatus(Integer... status){
        return eq("status", Arrays.asList(status));
    }

    public static Condition build() {
        return new Condition();
    }


    protected static String originalColumn(String column) {
        StringBuilder sb = new StringBuilder(column);
        int temp = 0;//定位
        for (int i = 0; i < column.length(); i++) {
            if (Character.isUpperCase(column.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }
}
