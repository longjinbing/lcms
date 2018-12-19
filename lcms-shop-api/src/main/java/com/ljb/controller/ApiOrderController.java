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
import com.ljb.entity.ApiOrder;
import com.ljb.service.ApiOrderService;

/**
 * 订单管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apiorder")
@MenuDescription(group="商城API",name="订单管理", action ="api/apiorder.html")
public class ApiOrderController extends BaseController {
    @Autowired
    private ApiOrderService apiOrderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="订单管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiOrderService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看订单管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiOrder apiOrder  = apiOrderService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiOrder));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="订单管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiOrder  = apiOrderService.selectMapById(id);
        return R.ok().put( apiOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加订单管理")
    public R save(@Validated(AddGroup.class) @RequestBody ApiOrder apiOrder) {
        apiOrderService.save(apiOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改订单管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiOrder apiOrder) {
        apiOrderService.update(apiOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除订单管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="订单管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiOrderService.export(jsonObject,response);
    }
}
