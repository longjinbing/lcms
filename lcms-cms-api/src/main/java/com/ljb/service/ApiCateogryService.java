package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiCateogry;

import java.util.List;
import java.util.Map;

/**
 * 目录Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiCateogryService extends BaseService<ApiCateogry,Long> {
List<Map<String,Object>> selectList();
}
