package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.GoodsGallery;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.GoodsGalleryService;
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
 * 商品展示管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */

@RestController
@RequestMapping("goods/goodsgallery")
@MenuDescription(group="商品中心",name="商品展示", action ="goods/goodsgallery.html")
public class GoodsGalleryController extends BaseController {
    @Autowired
    private GoodsGalleryService goodsGalleryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品展示管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( goodsGalleryService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品展示管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        GoodsGallery goodsGallery  = goodsGalleryService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(goodsGallery));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品展示管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> goodsGallery  = goodsGalleryService.selectMapById(id);
        return R.ok().put( goodsGallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品展示管理")
    public R save(@Validated(AddGroup.class) @RequestBody GoodsGallery goodsGallery) {
        goodsGalleryService.save(goodsGallery);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品展示管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody GoodsGallery goodsGallery) {
        goodsGalleryService.update(goodsGallery);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品展示管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        goodsGalleryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品展示管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        goodsGalleryService.export(jsonObject,response);
    }
}
