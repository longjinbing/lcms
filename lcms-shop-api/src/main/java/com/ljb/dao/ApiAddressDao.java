package com.ljb.dao;

import com.ljb.entity.ApiAddress;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 用户地址Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiAddressDao extends BaseDao<ApiAddress,Long> {

}
