package com.ljb.dao;

import com.ljb.entity.ArticleComment;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 文章评论管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */
@CacheNamespace(implementation= DaoCache.class)
public interface ArticleCommentDao extends BaseDao<ArticleComment,Long> {

}
