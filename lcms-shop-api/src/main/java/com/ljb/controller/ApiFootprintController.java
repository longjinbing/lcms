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
import com.ljb.entity.ApiFootprint;
import com.ljb.service.ApiFootprintService;

/**
 * 用户足迹Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apifootprint")
@MenuDescription(group="商城API",name="用户足迹", action ="api/apifootprint.html")
public class ApiFootprintController extends BaseController {
    @Autowired
    private ApiFootprintService apiFootprintService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="用户足迹列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiFootprintService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看用户足迹")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiFootprint apiFootprint  = apiFootprintService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiFootprint));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="用户足迹详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiFootprint  = apiFootprintService.selectMapById(id);
        return R.ok().put( apiFootprint);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加用户足迹")
    public R save(@Validated(AddGroup.class) @RequestBody ApiFootprint apiFootprint) {
        apiFootprintService.save(apiFootprint);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改用户足迹")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiFootprint apiFootprint) {
        apiFootprintService.update(apiFootprint);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除用户足迹")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiFootprintService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="用户足迹导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiFootprintService.export(jsonObject,response);
    }
}
