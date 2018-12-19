package com.ljb.dao;

import com.ljb.entity.GoodsSpecification;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 商品规格管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@CacheNamespace(implementation= DaoCache.class)
public interface GoodsSpecificationDao extends BaseDao<GoodsSpecification,Long> {

}
