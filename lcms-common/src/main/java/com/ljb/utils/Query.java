package com.ljb.utils;

import com.ljb.exception.DataException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询参数
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-14 23:15
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    //当前页码
    private Long offset;

    //每页条数
    private int limit;
    public Query(QueryParams queryParams){
        this.putAll(BeanUtils.bean2Map(queryParams, false));
    }

    public Query(Map<String, Object> params) {
        //分页参数
        try {
            this.offset =Long.valueOf(params.getOrDefault("offset", 0).toString());
            this.limit =Integer.valueOf(params.getOrDefault("limit", 10).toString());
            String field = params.getOrDefault("field", "").toString();
            String search = params.getOrDefault("text", "").toString();
            String sort = params.getOrDefault("sort", "").toString();
            String order = params.getOrDefault("order", "").toString();
            String stime = params.getOrDefault("stime", "").toString();
            String etime = params.getOrDefault("etime", "").toString();
            this.put("offset", offset);
            this.put("limit", limit);
            if (search.length()>0) {
                sqlValidate(field,search);
                this.put("field", upperCharToUnderLine(field));
                this.put("search", search);
            }
            if (sort.length()>0) {
                sqlValidate(sort,order);
                this.put("sort", upperCharToUnderLine(sort));
                this.put("order", order);
            }
            if (stime.length()>1 && etime.length()>1) {
                sqlValidate(stime,etime);
                this.put("stime", stime);
                this.put("etime", etime);
            }
            if(params.containsKey("pid")){
                sqlValidate(params.get("pid").toString());
                this.put("pid", params.get("pid"));
            }
            search = params.getOrDefault("search", "").toString();
            if(search.length()>0){
                this.put("search",search );
            }
            search = params.getOrDefault("categoryId", "").toString();
            if(search.length()>0){
                this.put("categoryId",search );
            }
        }catch (Exception e){
            throw e;
        }
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

    public static String upperCharToUnderLine(String param) {
        Pattern  p= Pattern.compile("[A-Z]");
        if(param==null ||param.equals("")){
            return "";
        }
        StringBuilder builder=new StringBuilder(param);
        Matcher mc=p.matcher(param);
        int i=0;
        while (mc.find()) {
            builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
            i++;
        }

        if('_' == builder.charAt(0)){
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    public void sqlValidate(String... arr) {
        for(String str:arr) {
            str = str.toLowerCase();//统一转为小写
            String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                    "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                    "table|from|grant|use|group_concat|column_name|" +
                    "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                    "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
            String[] badStrs = badStr.split("\\|");
            for (int i = 0; i < badStrs.length; i++) {
                if (str.indexOf(badStrs[i]) >= 0) {
                    throw new DataException(400,"SQL风险");
                }
            }
        }
    }
}
