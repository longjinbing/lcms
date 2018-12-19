package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiAddress;

import java.util.List;
import java.util.Map;

/**
 * 用户地址Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiAddressService extends BaseService<ApiAddress,Long> {
    List<Map<String,Object>> addressList();
}
