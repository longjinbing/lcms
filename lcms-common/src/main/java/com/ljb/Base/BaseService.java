package com.ljb.Base;

import com.alibaba.fastjson.JSONObject;
import com.ljb.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */
public interface BaseService<T,K> {
    PageUtils selectList(Map<String,Object> map);

    T selectById(K id);

    Map<String,Object> selectMapById(K id);

    int deleteBatch(List<K> ids);

    int save(T entity);

    int update(T entity);

    void export(JSONObject jsonObject, HttpServletResponse response);
}
