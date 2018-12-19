package com.ljb.dao;

import com.ljb.entity.ApiSmsLog;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 短信日志Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiSmsLogDao extends BaseDao<ApiSmsLog,Long> {

}
