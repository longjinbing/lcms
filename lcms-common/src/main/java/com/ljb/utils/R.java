package com.ljb.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longjinbin
 * @email 939961241@qq.com
 * @date 2018年11月30日 下午20:52:36
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 200);
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R put(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R judge(Boolean result) {
        R r = result?R.ok("操作成功"):R.error(400,"操作失败" );
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
