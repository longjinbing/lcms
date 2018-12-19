package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.OrderGoods;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.OrderGoodsService;
import com.ljb.utils.BeanUtils;
import com.ljb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 订单商品管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */

@RestController
@RequestMapping("order/ordergoods")
@MenuDescription(group="订单中心",name="订单商品", action ="order/ordergoods.html")
public class OrderGoodsController extends BaseController {
    @Autowired
    private OrderGoodsService orderGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="订单商品管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( orderGoodsService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看订单商品管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        OrderGoods orderGoods  = orderGoodsService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(orderGoods));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="订单商品管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> orderGoods  = orderGoodsService.selectMapById(id);
        return R.ok().put( orderGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加订单商品管理")
    public R save(@Validated(AddGroup.class) @RequestBody OrderGoods orderGoods) {
        orderGoodsService.save(orderGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改订单商品管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody OrderGoods orderGoods) {
        orderGoodsService.update(orderGoods);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除订单商品管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        orderGoodsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="订单商品管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        orderGoodsService.export(jsonObject,response);
    }
}
