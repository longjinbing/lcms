package com.ljb.dao;

import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import com.ljb.entity.Menu;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 菜单管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface MenuDao extends BaseDao<Menu,Long> {
    List<Menu> menuList(Long id);

    List<String> actionList(Long id);

    List<String> allActions();

    List<Menu> findByStatusAndUserId(Long id);

    List<Menu> findByUserId(Long id);

    List<Menu>  findByRoleId(Long id);

}
