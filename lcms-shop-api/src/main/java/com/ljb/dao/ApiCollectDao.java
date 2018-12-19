package com.ljb.dao;

import com.ljb.entity.ApiCollect;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 商品收藏Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiCollectDao extends BaseDao<ApiCollect,Long> {

}
