package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiGoodsCategory;

import java.util.List;
import java.util.Map;

/**
 * 商品分类Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiGoodsCategoryService extends BaseService<ApiGoodsCategory,Long> {
    List<Map<String,Object>> selectList();
}
