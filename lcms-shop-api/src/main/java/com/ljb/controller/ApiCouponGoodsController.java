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
import com.ljb.entity.ApiCouponGoods;
import com.ljb.service.ApiCouponGoodsService;

/**
 * 优惠券关联商品Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apicoupongoods")
@MenuDescription(group="商城API",name="优惠券关联商品", action ="api/apicoupongoods.html")
public class ApiCouponGoodsController extends BaseController {
    @Autowired
    private ApiCouponGoodsService apiCouponGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="优惠券关联商品列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiCouponGoodsService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看优惠券关联商品")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiCouponGoods apiCouponGoods  = apiCouponGoodsService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiCouponGoods));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="优惠券关联商品详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiCouponGoods  = apiCouponGoodsService.selectMapById(id);
        return R.ok().put( apiCouponGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加优惠券关联商品")
    public R save(@Validated(AddGroup.class) @RequestBody ApiCouponGoods apiCouponGoods) {
        apiCouponGoodsService.save(apiCouponGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改优惠券关联商品")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiCouponGoods apiCouponGoods) {
        apiCouponGoodsService.update(apiCouponGoods);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除优惠券关联商品")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiCouponGoodsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="优惠券关联商品导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiCouponGoodsService.export(jsonObject,response);
    }
}
