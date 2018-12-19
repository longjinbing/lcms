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
import com.ljb.entity.ApiShipping;
import com.ljb.service.ApiShippingService;

/**
 * 快递信息Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apishipping")
@MenuDescription(group="商城API",name="快递信息", action ="api/apishipping.html")
public class ApiShippingController extends BaseController {
    @Autowired
    private ApiShippingService apiShippingService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="快递信息列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiShippingService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看快递信息")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiShipping apiShipping  = apiShippingService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiShipping));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="快递信息详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiShipping  = apiShippingService.selectMapById(id);
        return R.ok().put( apiShipping);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加快递信息")
    public R save(@Validated(AddGroup.class) @RequestBody ApiShipping apiShipping) {
        apiShippingService.save(apiShipping);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改快递信息")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiShipping apiShipping) {
        apiShippingService.update(apiShipping);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除快递信息")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiShippingService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="快递信息导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiShippingService.export(jsonObject,response);
    }
}
