package com.ljb.dao;

import com.ljb.entity.SmsLog;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 短信日志管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface SmsLogDao extends BaseDao<SmsLog,Long> {

}
