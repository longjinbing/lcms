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
import com.ljb.entity.ApiOrderGoods;
import com.ljb.service.ApiOrderGoodsService;

/**
 * 订单商品Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apiordergoods")
@MenuDescription(group="商城API",name="订单商品", action ="api/apiordergoods.html")
public class ApiOrderGoodsController extends BaseController {
    @Autowired
    private ApiOrderGoodsService apiOrderGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="订单商品列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiOrderGoodsService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看订单商品")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiOrderGoods apiOrderGoods  = apiOrderGoodsService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiOrderGoods));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="订单商品详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiOrderGoods  = apiOrderGoodsService.selectMapById(id);
        return R.ok().put( apiOrderGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加订单商品")
    public R save(@Validated(AddGroup.class) @RequestBody ApiOrderGoods apiOrderGoods) {
        apiOrderGoodsService.save(apiOrderGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改订单商品")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiOrderGoods apiOrderGoods) {
        apiOrderGoodsService.update(apiOrderGoods);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除订单商品")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiOrderGoodsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="订单商品导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiOrderGoodsService.export(jsonObject,response);
    }
}
