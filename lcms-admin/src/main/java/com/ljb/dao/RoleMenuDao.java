package com.ljb.dao;

import com.ljb.entity.RoleMenu;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 角色菜单管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface RoleMenuDao extends BaseDao<RoleMenu,Long> {

}
