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
import com.ljb.entity.ApiGoodsGallery;
import com.ljb.service.ApiGoodsGalleryService;

/**
 * 商品展示Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apigoodsgallery")
@MenuDescription(group="商城API",name="商品展示", action ="api/apigoodsgallery.html")
public class ApiGoodsGalleryController extends BaseController {
    @Autowired
    private ApiGoodsGalleryService apiGoodsGalleryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品展示列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiGoodsGalleryService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品展示")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiGoodsGallery apiGoodsGallery  = apiGoodsGalleryService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiGoodsGallery));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品展示详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiGoodsGallery  = apiGoodsGalleryService.selectMapById(id);
        return R.ok().put( apiGoodsGallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品展示")
    public R save(@Validated(AddGroup.class) @RequestBody ApiGoodsGallery apiGoodsGallery) {
        apiGoodsGalleryService.save(apiGoodsGallery);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品展示")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiGoodsGallery apiGoodsGallery) {
        apiGoodsGalleryService.update(apiGoodsGallery);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品展示")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiGoodsGalleryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品展示导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiGoodsGalleryService.export(jsonObject,response);
    }
}
