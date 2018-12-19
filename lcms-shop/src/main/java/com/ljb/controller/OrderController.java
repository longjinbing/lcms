package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Order;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.OrderService;
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
 * 订单管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */

@RestController
@RequestMapping("order/order")
@MenuDescription(group="订单中心",name="订单管理", action ="order/order.html")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="订单管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( orderService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看订单管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Order order  = orderService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(order));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="订单管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> order  = orderService.selectMapById(id);
        return R.ok().put( order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加订单管理")
    public R save(@Validated(AddGroup.class) @RequestBody Order order) {
        orderService.save(order);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改订单管")
    public R update(@Validated(UpdateGroup.class) @RequestBody Order order) {
        orderService.update(order);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除订单管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        orderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="订单管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        orderService.export(jsonObject,response);
    }
}
