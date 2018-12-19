package com.ljb.dao;

import com.ljb.entity.Attribute;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 体质属性Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@CacheNamespace(implementation= DaoCache.class)
public interface AttributeDao extends BaseDao<Attribute,Long> {

}
