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
import com.ljb.entity.ApiGoodsCategory;
import com.ljb.service.ApiGoodsCategoryService;

/**
 * 商品分类Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apigoodscategory")
@MenuDescription(group="商城API",name="商品分类", action ="api/apigoodscategory.html")
public class ApiGoodsCategoryController extends BaseController {
    @Autowired
    private ApiGoodsCategoryService apiGoodsCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品分类列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiGoodsCategoryService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品分类")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiGoodsCategory apiGoodsCategory  = apiGoodsCategoryService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiGoodsCategory));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品分类详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiGoodsCategory  = apiGoodsCategoryService.selectMapById(id);
        return R.ok().put( apiGoodsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品分类")
    public R save(@Validated(AddGroup.class) @RequestBody ApiGoodsCategory apiGoodsCategory) {
        apiGoodsCategoryService.save(apiGoodsCategory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品分类")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiGoodsCategory apiGoodsCategory) {
        apiGoodsCategoryService.update(apiGoodsCategory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品分类")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiGoodsCategoryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品分类导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiGoodsCategoryService.export(jsonObject,response);
    }
}
