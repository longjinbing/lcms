package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiCollect;

/**
 * 商品收藏Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiCollectService extends BaseService<ApiCollect,Long> {
Boolean add(Long addid);
    Boolean remove(Long addid);
}
