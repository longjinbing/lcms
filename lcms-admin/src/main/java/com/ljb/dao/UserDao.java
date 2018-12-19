package com.ljb.dao;

import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import com.ljb.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * 系统用户管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface UserDao extends BaseDao<User,Long> {
    User findByCode(String number);
    List<Map<String, Object>> selectMapTList(Map<String, Object> map);
}
