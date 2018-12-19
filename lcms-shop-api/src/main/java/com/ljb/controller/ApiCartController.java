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
import com.ljb.entity.ApiCart;
import com.ljb.service.ApiCartService;

/**
 * 购物缓存Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apicart")
@MenuDescription(group="商城API",name="购物缓存", action ="api/apicart.html")
public class ApiCartController extends BaseController {
    @Autowired
    private ApiCartService apiCartService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="购物缓存列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiCartService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看购物缓存")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiCart apiCart  = apiCartService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiCart));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="购物缓存详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiCart  = apiCartService.selectMapById(id);
        return R.ok().put( apiCart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加购物缓存")
    public R save(@Validated(AddGroup.class) @RequestBody ApiCart apiCart) {
        apiCartService.save(apiCart);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改购物缓存")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiCart apiCart) {
        apiCartService.update(apiCart);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除购物缓存")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiCartService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="购物缓存导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiCartService.export(jsonObject,response);
    }
}
