package com.ljb.dao;

import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import com.ljb.entity.Config;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * 系统配置管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ConfigDao extends BaseDao<Config,Long> {
 List<Map<String,Object>> findByItem(String item);

Integer findMaxByparentId(Long parentId);
}
