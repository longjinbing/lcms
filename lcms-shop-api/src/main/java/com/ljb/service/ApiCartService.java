package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiCart;

import java.util.List;
import java.util.Map;

/**
 * 购物缓存Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiCartService extends BaseService<ApiCart,Long> {
    Boolean clearCart();
    List<Map<String,Object>> cartList();
    Boolean add(Long id);
    Boolean remove(Long id);
    Boolean removeAll();
    Boolean addNumber(Long id);
    Boolean subNumber(Long id);
}
