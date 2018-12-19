package com.ljb.dao;

import com.ljb.entity.Log;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 系统日志管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface LogDao extends BaseDao<Log,Long> {

}
