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
import com.ljb.entity.ApiCoupon;
import com.ljb.service.ApiCouponService;

/**
 * 商品优惠卷Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apicoupon")
@MenuDescription(group="商城API",name="商品优惠卷", action ="api/apicoupon.html")
public class ApiCouponController extends BaseController {
    @Autowired
    private ApiCouponService apiCouponService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品优惠卷列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiCouponService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品优惠卷")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiCoupon apiCoupon  = apiCouponService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiCoupon));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品优惠卷详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiCoupon  = apiCouponService.selectMapById(id);
        return R.ok().put( apiCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品优惠卷")
    public R save(@Validated(AddGroup.class) @RequestBody ApiCoupon apiCoupon) {
        apiCouponService.save(apiCoupon);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品优惠卷")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiCoupon apiCoupon) {
        apiCouponService.update(apiCoupon);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品优惠卷")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiCouponService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品优惠卷导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiCouponService.export(jsonObject,response);
    }
}
