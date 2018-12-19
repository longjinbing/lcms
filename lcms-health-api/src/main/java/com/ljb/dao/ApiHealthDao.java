package com.ljb.dao;

import java.util.List;
import java.util.Map;

/**
 * 体质测试问题Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
public interface ApiHealthDao {
    List<Map<String,Object>> questionList();

    List<Map<String,Long>> selectList();

    List<Map<String,Object>> categoryList();

    Map<String,Object> categoryById(Long id);
}
