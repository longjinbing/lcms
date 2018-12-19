package com.ljb.controller;

import java.util.*;
import com.ljb.annotion.*;
import com.ljb.model.*;
import com.ljb.utils.*;
import com.ljb.Base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.ljb.entity.ApiUserCoupon;
import com.ljb.service.ApiUserCouponService;

/**
 * 用户优惠卷Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apiusercoupon")
@MenuDescription(group="系统管理",name="用户优惠卷", action ="api/apiusercoupon.html")
public class ApiUserCouponController extends BaseController {
    @Autowired
    private ApiUserCouponService apiUserCouponService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="用户优惠卷列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiUserCouponService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看用户优惠卷")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiUserCoupon apiUserCoupon  = apiUserCouponService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiUserCoupon));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="用户优惠卷详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiUserCoupon  = apiUserCouponService.selectMapById(id);
        return R.ok().put( apiUserCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加用户优惠卷")
    public R save(@Validated(AddGroup.class) @RequestBody ApiUserCoupon apiUserCoupon) {
        apiUserCouponService.save(apiUserCoupon);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改用户优惠卷")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiUserCoupon apiUserCoupon) {
        apiUserCouponService.update(apiUserCoupon);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除用户优惠卷")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiUserCouponService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="用户优惠卷导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiUserCouponService.export(jsonObject,response);
    }
}
