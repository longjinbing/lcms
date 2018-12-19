package com.ljb.dao;

import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import com.ljb.entity.Role;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 角色管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface RoleDao extends BaseDao<Role,Long> {
    List<Role> findByUserId(Long id);

    List<String> roleList(Long id);
}
