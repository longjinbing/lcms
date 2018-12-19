package com.ljb.dao;

import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import com.ljb.entity.Category;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * 体质分类Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@CacheNamespace(implementation= DaoCache.class)
public interface CategoryDao extends BaseDao<Category,Long> {
    List<Map<String,Object>> categoryList();
}
