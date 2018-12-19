package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiOrder;
import com.ljb.model.OrderModel;

import java.util.Map;

/**
 * 订单管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiOrderService extends BaseService<ApiOrder,Long> {
   Map<String,Object> save(OrderModel orderModel);
}
