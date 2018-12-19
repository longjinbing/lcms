package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.ApiUserCoupon;

/**
 * 用户优惠卷Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
public interface ApiUserCouponService extends BaseService<ApiUserCoupon,Long> {
    Boolean getCoupon(Long id);
}
