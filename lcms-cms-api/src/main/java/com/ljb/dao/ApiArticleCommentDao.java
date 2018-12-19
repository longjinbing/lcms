package com.ljb.dao;

import com.ljb.entity.ApiArticleComment;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 文章评论Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ApiArticleCommentDao extends BaseDao<ApiArticleComment,Long> {

}
