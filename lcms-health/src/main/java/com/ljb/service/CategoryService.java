package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Category;

import java.util.List;
import java.util.Map;

/**
 * 体质分类Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
public interface CategoryService extends BaseService<Category,Long> {
    List<Map<String,Object>> categoryList();
}
