package com.ljb.dao;

import com.ljb.entity.ApiFootprint;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 用户足迹Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiFootprintDao extends BaseDao<ApiFootprint,Long> {

}
