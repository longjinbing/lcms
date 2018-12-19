package com.ljb.dao;

import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import com.ljb.entity.Article;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 文章管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ArticleDao extends BaseDao<Article,Long> {
    int publish(List<Long> ids);
    int stopPublish(List<Long> ids);
}
