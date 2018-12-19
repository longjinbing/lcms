package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiOrderGoods;

import java.util.List;

/**
 * 订单商品Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiOrderGoodsService extends BaseService<ApiOrderGoods,Long> {
List<ApiOrderGoods> orderGoods(Long id);
}
