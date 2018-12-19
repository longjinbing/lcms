package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.GoodsCategory;

import java.util.List;
import java.util.Map;

/**
 * 商品分类管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
public interface GoodsCategoryService extends BaseService<GoodsCategory, Long> {
    List<Map<String, Object>> treeList();
}
