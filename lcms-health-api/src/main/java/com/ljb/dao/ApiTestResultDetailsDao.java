package com.ljb.dao;

import com.ljb.entity.ApiTestResultDetails;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 体质测试结果详情Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiTestResultDetailsDao extends BaseDao<ApiTestResultDetails,Long> {

}
