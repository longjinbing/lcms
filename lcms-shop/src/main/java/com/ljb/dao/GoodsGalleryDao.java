package com.ljb.dao;

import com.ljb.entity.GoodsGallery;
import com.ljb.Base.BaseDao;
import com.ljb.cache.DaoCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 商品展示管理Dao
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@CacheNamespace(implementation= DaoCache.class)
public interface GoodsGalleryDao extends BaseDao<GoodsGallery,Long> {

}
