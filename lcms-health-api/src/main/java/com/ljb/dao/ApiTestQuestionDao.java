package com.ljb.dao;

import com.ljb.entity.ApiTestQuestion;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 体质测试问题Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiTestQuestionDao extends BaseDao<ApiTestQuestion,Long> {

}
