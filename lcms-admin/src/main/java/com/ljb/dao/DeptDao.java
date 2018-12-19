package com.ljb.dao;

import com.ljb.entity.Dept;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 部门管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface DeptDao extends BaseDao<Dept,Long> {
    Dept findByRole(Long id);
}
