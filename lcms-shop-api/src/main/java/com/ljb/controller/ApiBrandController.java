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
import com.ljb.entity.ApiBrand;
import com.ljb.service.ApiBrandService;

/**
 * 品牌管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apibrand")
@MenuDescription(group="商城API",name="品牌管理", action ="api/apibrand.html")
public class ApiBrandController extends BaseController {
    @Autowired
    private ApiBrandService apiBrandService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="品牌管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiBrandService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看品牌管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiBrand apiBrand  = apiBrandService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiBrand));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="品牌管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiBrand  = apiBrandService.selectMapById(id);
        return R.ok().put( apiBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加品牌管理")
    public R save(@Validated(AddGroup.class) @RequestBody ApiBrand apiBrand) {
        apiBrandService.save(apiBrand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改品牌管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiBrand apiBrand) {
        apiBrandService.update(apiBrand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除品牌管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiBrandService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="品牌管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiBrandService.export(jsonObject,response);
    }
}
