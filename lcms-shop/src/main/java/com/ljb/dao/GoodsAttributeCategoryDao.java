package com.ljb.dao;

import com.ljb.entity.GoodsAttributeCategory;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 商品属性类型管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@CacheNamespace(implementation= DaoCache.class)
public interface GoodsAttributeCategoryDao extends BaseDao<GoodsAttributeCategory,Long> {

}
